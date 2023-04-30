curl -X POST "http://localhost:1453/api/auth/register/" \
   -H "Content-Type: application/json" \
   -d '{"username": "john", "password": "12345678", "roles": ["JUDGE", "USER"] }'
curl -X POST "http://localhost:1453/api/auth/register/" \
   -H "Content-Type: application/json" \
   -d '{"username": "brown", "password": "12345678", "roles": ["JUDGE", "USER"] }'
curl -X POST "http://localhost:1453/api/auth/register/" \
   -H "Content-Type: application/json" \
   -d '{"username": "smith", "password": "12345678", "roles": ["MODERATOR", "USER"] }'


curl -u smith:12345678 -X GET "http://localhost:1453/api/user/me/"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team1" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team2" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team3" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team4" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team5" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team6" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team7" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team8" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team9" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team10" \
   -H "Content-Type: application/json"



curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/tournaments/" \
   -H "Content-Type: application/json" \
   -d '{"name": "123s32322", "startDate": "2022-10-10", "maxGames": 3, "approvalRatio": 0.6, "judgesIds": [1, 2], "teamsIds": [1, 2, 3, 4, 5] }'


curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/2/play-game/?winnerId=2" \
   -H "Content-Type: application/json"
curl -u john:12345678  -X POST "http://localhost:1453/api/judge/games/1/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678  -X POST "http://localhost:1453/api/judge/games/1/approve/" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/2/play-game/?winnerId=2" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/2/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/2/approve/" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/2/play-game/?winnerId=2" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/3/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/3/approve/" \
   -H "Content-Type: application/json"

# Match 3
curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/3/play-game/?winnerId=3" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/4/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/4/approve/" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/3/play-game/?winnerId=3" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/5/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/5/approve/" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/3/play-game/?winnerId=3" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/6/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/6/approve/" \
   -H "Content-Type: application/json"



# Match 5
curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/5/play-game/?winnerId=5" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/7/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/7/approve/" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/5/play-game/?winnerId=5" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/8/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/8/approve/" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/5/play-game/?winnerId=2" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/9/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/9/approve/" \
   -H "Content-Type: application/json"


# Match 6
curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/6/play-game/?winnerId=5" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/10/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/10/approve/" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/6/play-game/?winnerId=3" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/11/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/11/approve/" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/6/play-game/?winnerId=3" \
   -H "Content-Type: application/json"
curl -u john:12345678 -X POST "http://localhost:1453/api/judge/games/12/approve/" \
   -H "Content-Type: application/json"
curl -u brown:12345678 -X POST "http://localhost:1453/api/judge/games/12/approve/" \
   -H "Content-Type: application/json"


curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/matches/2/drop/" \
   -H "Content-Type: application/json"