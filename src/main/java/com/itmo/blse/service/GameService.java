package com.itmo.blse.service;

import com.itmo.blse.errors.ValidationError;
import com.itmo.blse.model.*;
import com.itmo.blse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

enum MatchStatus {
    FINISHED, IN_PROGRESS, VOTING
}

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    GameVoteRepository gameVoteRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    UserService userService;

    @Autowired
    TeamRepository teamRepository;

    private MatchStatus updateAndGetMatchStatus(Match match) {
        Tournament tournament = match.getTournament();

        List<Game> games = gameRepository.getGamesByMatch(match);
        int gamesApproved = 0;
        int team1Wins = 0, team2Wins = 0;
        for (Game game : games) {
            List<GameVote> gameVotes = gameVoteRepository.getGameVotesByGame(game);
            int total = gameVotes.size();
            int positive = (int) gameVotes.stream().filter(GameVote::isApproved).count();
            if (total == tournament.getJudges().size()) {
                if ((double) positive / total >= tournament.getApprovalRatio()) {
                    gamesApproved++;
                    if (game.getWinner() == match.getTeam1()) {
                        team1Wins++;
                    } else if (game.getWinner() == match.getTeam2()) {
                        team2Wins++;
                    } else {
                        assert false;
                    }
                } else {
                    gameRepository.delete(game);
                }
            } else {
                return MatchStatus.VOTING;
            }
        }
        if (gamesApproved == games.size() && games.size() == tournament.getMaxGames()) {
            Match next = match.getNextMatch();
            Team winner = team1Wins > team2Wins ? match.getTeam1() : match.getTeam2();
            if (next != null) {
                Long team1Id = next.getTeam1() != null ? next.getTeam1().getId() : null;
                Long team2Id = next.getTeam2() != null ? next.getTeam2().getId() : null;
                if (!Objects.equals(team1Id, winner.getId()) && !Objects.equals(team2Id, winner.getId())) {
                    if (team1Id == null) {
                        next.setTeam1(winner);
                    } else if (team2Id == null) {
                        next.setTeam2(winner);
                    } else {
                        assert false;
                    }
                    matchRepository.save(next);
                    match = next;
                    next = match.getNextMatch();
                    while (next != null && matchRepository.getAllByNextMatch(match).size() == 1) {
                        next.setTeam1(winner);
                        matchRepository.save(next);
                        match = next;
                        next = match.getNextMatch();
                    }
                }
            } else {
                tournament.setWinner(winner);
                tournamentRepository.save(tournament);
            }
            return MatchStatus.FINISHED;
        } else {
            return MatchStatus.IN_PROGRESS;
        }
    }

    @Transactional
    public Tournament dropMatch(Long id) throws ValidationError {
        Match match = matchRepository.getMatchById(id);
        if (match == null) {
            throw new ValidationError(List.of("Match not found"));
        }
        Match next = match.getNextMatch();
        gameRepository.deleteAllByMatch(match);


        while (next != null) {
            Long team1Id = match.getTeam1() != null ? match.getTeam1().getId() : null;
            Long team2Id = match.getTeam2() != null ? match.getTeam2().getId() : null;
            Long nextTeam1Id = next.getTeam1() != null ? next.getTeam1().getId() : null;
            Long nextTeam2Id = next.getTeam2() != null ? next.getTeam2().getId() : null;
            Long winnerId = (Objects.equals(nextTeam1Id, team1Id) || Objects.equals(nextTeam2Id, team1Id)) ? team1Id : team2Id;

            if (Objects.equals(winnerId, nextTeam1Id)) {
                next.setTeam1(null);
            }
            if (Objects.equals(winnerId, nextTeam2Id)) {
                next.setTeam2(null);
            }
            gameRepository.deleteAllByMatch(next);
            match = next;
            next = next.getNextMatch();
        }
        Tournament tournament = match.getTournament();
        tournament.setWinner(null);
        tournamentRepository.save(tournament);
        return tournament;
    }

    @Transactional
    public Match playGame(Long id, Long winnerId) throws ValidationError {
        Match match = matchRepository.getMatchById(id);
        Team winner = teamRepository.getTeamById(winnerId);

        if (match == null) {
            throw new ValidationError(List.of("Match not found"));
        }
        if (winner == null) {
            throw new ValidationError(List.of("Winner not found"));
        }
        if (match.getTeam1() != winner && match.getTeam2() != winner) {
            throw new ValidationError(List.of("Winner does not belong to match"));
        }
        MatchStatus status = updateAndGetMatchStatus(match);
        if (status == MatchStatus.VOTING) {
            throw new ValidationError(List.of("Can't play while the last game is not approved"));
        }
        if (status == MatchStatus.FINISHED) {
            throw new ValidationError(List.of("Can't play when match is finished"));
        }

        Game game = new Game();
        game.setWinner(winner);
        game.setMatch(match);
        gameRepository.save(game);

        return match;
    }

    @Transactional
    public Game approveGame(Long id, boolean isApproved) throws ValidationError {
        User user = userService.fromContext();

        Game game = gameRepository.getGameById(id);
        if (game == null) {
            throw new ValidationError(List.of("Game not found"));
        }
        Tournament tournament = game.getMatch().getTournament();
        if (tournament.getJudges().stream().noneMatch(judge -> judge.getId().equals(user.getId()))) {
            throw new ValidationError(List.of("User is not a judge"));
        }

        if (gameVoteRepository.getGameVotesByGame(game).size() == tournament.getMaxGames()) {
            throw new ValidationError(List.of("Game is already finished"));
        }

        gameVoteRepository.deleteAllByGameAndJudge(game, user);

        GameVote gameVote = new GameVote();
        gameVote.setGame(game);
        gameVote.setApproved(isApproved);
        gameVote.setJudge(user);
        gameVoteRepository.save(gameVote);

        updateAndGetMatchStatus(game.getMatch());

        return game;
    }
}
