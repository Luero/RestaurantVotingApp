## RestaurantVotingApp
REST API app where users can vote for the restaurant to go

### Task description

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote for a restaurant they want to have lunch at today
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

### Technologies used

Maven, Spring Boot, Spring MVC, Spring Security, Spring Data JPA, Hibernate, REST(Jackson), JUnit, Swagger, H2 database

### Documentation

Link to Swagger UI: http://localhost:8080/swagger-ui/index.html

Credentials for test:
- for User: user@yandex.ru / password or victor@yandex.ru / victor
- for Admin: admin@yandex.ru / admin
