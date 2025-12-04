## Search API Support

The API supports several search features for both Members and Tournaments.

### Member Search Endpoints
- **Search by name:**  
  `GET /api/members/search/by-name?name={name}`

- **Search by membership type:**  
  `GET /api/members/search/by-membership-type?type={type}`

- **Search by phone number:**  
  `GET /api/members/search/by-phone?phone={number}`

- **Search members by tournament start date:**  
  `GET /api/members/search/by-tournament-start-date?date={YYYY-MM-DD}`

### Tournament Search Endpoints
- **Search tournaments by start date:**  
  `GET /api/tournaments/search/by-start-date?date={YYYY-MM-DD}`

- **Search tournaments by location:**  
  `GET /api/tournaments/search/by-location?location={location}`

- **Find all members in a tournament:**  
  `GET /api/tournaments/{tournamentId}/members`

These endpoints were all tested using Postman, and the required screenshots have been included.

---

## Running the Project in Docker

The project includes full Docker support using **docker-compose**.

### Requirements
- Docker Desktop running
- No other service using ports 8080 or 3307

### Steps to Run

From the project root, run:

bash:
docker compose up --build


## AWS RDS
I successfully launched the amazon db once by going into the aurora and db page, hitting create db and creating golfclubdb with public access enabled and the correct changes made to my properties file. I did not take any screenshots of this before the aws learner lab timed out. While putting all of my project materials together and writing this readme I tried to once again launch the db in amazon only to receive an error block saying I lack the permissions. 




