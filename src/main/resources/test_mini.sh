curl -X POST "http://localhost:1453/api/auth/register/" \
   -H "Content-Type: application/json" \
   -d '{"username": "john", "password": "12345678", "roles": ["JUDGE", "USER"] }'
curl -X POST "http://localhost:1453/api/auth/register/" \
   -H "Content-Type: application/json" \
   -d '{"username": "brown", "password": "12345678", "roles": ["JUDGE", "USER"] }'
curl -X POST "http://localhost:1453/api/auth/register/" \
   -H "Content-Type: application/json" \
   -d '{"username": "smith", "password": "12345678", "roles": ["MODERATOR", "USER"] }'


curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team1" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/teams/?name=team2" \
   -H "Content-Type: application/json"

curl -u smith:12345678 -X POST "http://localhost:1453/api/moderator/tournaments/" \
   -H "Content-Type: application/json" \
   -d '{"name": "123s32322", "startDate": "2022-10-10", "maxGames": 3, "approvalRatio": 0.6, "judgesIds": [1, 2], "teamsIds": [1, 2] }'

