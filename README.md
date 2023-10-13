<h1 align = "center"><img alt="SpringBoot" src="https://spring.io/img/logos/spring-initializr.svg" width=5% height=5%/> Spring Boot <i>"Game Room"</i> Project <img alt="SpringBoot" src="https://spring.io/img/logos/spring-initializr.svg" width=5% height=5%/></h1>

This Spring Boot Project is divided into 2 parts:
- Handiling HTTP requests ➡️ branch: <i>only_api</i>
- Registration, Login, Email and Secure endpoints ➡️ branch: <i>login-registration-backend</i>

Main branch contains both parts as one project.

<sub><i>For more info check ReadMe.md in branches.</i></sub>

<h2>Contents: </h2>

- [Project requirements](#project-requirements)
- [Main idea](#main-idea)
- [Preparations](#preparations)
- [Preview](#preview)
  - [Pics From Final Project](#pics-from-final-project)
- [How to run](#how-to-run)
- [Available endpoints](#available-endpoints)
- [Wiki](#wiki)

## Project requirements

- Spring Boot Version: 3.1.4.
- Java Version: 21
- Dependencies:
  - Spring Data JPA,
  - Spring Web,
  - Postgre SQL Driver,
  - Thymeleaf,
  - Dotenv:
    - groupId: io.github.cdimascio,
    - artifactId: java-dotenv,
    - version: 5.2.2
  - Spring Security,
  - Java Mail Sender,
  - ~~Lombok~~

All the front-end is done by using [*Thymeleaf*](https://www.thymeleaf.org/) dependecy, simple HTML files with Bootstrap libraries.

## Main idea

The _"Game Room"_ project is designed to monitor the availability of eachPC and its current player. The provided tables present a list of available PCs and the Players currently using them. Player can't be stored into database/table if he's not using the PC.

## Preparations

Using [*Spring Initializr*](https://start.spring.io/) to set up Spring Boot project:

- Project: Maven
- Language: Java,
- Spring Boot: 3.1.4,
- Packaging: Jar,
- Java: 21

Installing Postgres: [*PostgreSQL Server for Windows*](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

- Port: 5432,
- Locale: [Default locale],
- *It's not needed to install Stack Builder*.

Basic commands:

`\l` for available databases,

`\c` for connecting to database (example: `\c gameroom`),

`\d` for listing relations (example: `\d gameroom`)

Creating database for project (*from SQL Shell psql*):

<pre>CREATE DATABASE gameroom;
GRANT ALL PRIVILEGES ON DATABASE "[<i>username</i>]" TO gameroom;</pre>

## Preview

Person after registration becomes the App User.

<p align="center">
<img alt = "pic" src="https://github.com/fajni/Game_Room/assets/87950386/6dcb7db8-2d05-47c7-b8bb-cc0c8d17aeb9" height="80%" width="80%"/>
</p>

### Pics From Final Project
<details>
  <summary>Pics</summary>
  <p align = "center">
    <br/>
    <b>Registration and Login custom page.</b>
    <img src="https://github.com/fajni/Game_Room/assets/87950386/c62923e0-e6dc-4a8b-8f6d-deaf634b20d3" name="Login_and_Registration" height="80%" width="80%"/>
    <br/>
    <b>Email that has been send to Person's mail:</b><br/>
    <img src="https://github.com/fajni/Game_Room/assets/87950386/efb78b04-ba0e-4299-acf9-a187678f035a" name="Email that has been send!" height="50%" width="50%"/>
    <br/>
    <b>Available PCs after succesfull registration.</b> Same goes with Players.<br/>
    <img src="https://github.com/fajni/Game-Room/assets/87950386/5b29740e-aa4d-4bb7-b64b-28227bdce5c1" name="Available PCs after registration." height="50%" width="50%">
    <br/>
  </p>
  <i>Note: Some pictures are outdated.</i>
</details>

## How to run

Clone project: `git clone https://github.com/fajni/Game_Room.git`

Clean Maven project: `mvn clean package` 

Install Maven project.

From _terminal_ locate youself in _"taget"_ folder (`cd target`), and run the application: `java -jar game-0.0.1-SNAPSHOT.jar`

_Note:_ To run multiple instances it's necessary to specify a port for each one individually: `java -jar game-0.0.1-SNAPSHOT.jar --server.port=8081`

<hr/>

Generating the Site: `mvn site`

## Available endpoints

RegistrationController:

|Request|Link|
|:---:|:---|
|GET|`localhost:8080/api/game/login`|
|GET|`localhost:8080/api/game/sign_up`|
|GET|`localhost:8080/api/game/confirm?token={token}`|
|---|---|
|POST|`localhost:8080/api/game/saveAppUser`|

PcController:

|Request|Link|
|:---:|:---|
|GET|`localhost:8080/api/game/pc`|
|GET|`localhost:8080/api/game/pc/`|
|GET|`localhost:8080/api/game/pc/json`|
|GET|`localhost:8080/api/game/pc/{pcNumber}`|
|GET|`localhost:8080/api/game/pc/removePc`|
|GET|`localhost:8080/api/game/pc/create_pc`|
|GET|`localhost:8080/api/game/pc/update_pc`|
|GET|`localhost:8080/api/game/pc/search?pcNumber={pcNumber}`|
|---|---|
|POST|`localhost:8080/api/game/pc`|
|POST|`localhost:8080/api/game/pc/savePc`|
|POST|`localhost:8080/api/game/pc/updatePc`|
|---|---|
|DELETE|`localhost:8080/api/game/pc/delete/{pcNumber}`|
|---|---|
|PUT|`localhost:8080/api/game/pc/{pcNumber}`|


PlayerController:

|Request|Link|
|:---:|:---|
|GET|`localhost:8080/api/game/player, localhost:8080/api/game/player/`|
|GET|`localhost:8080/api/game/player/json`|
|GET|`localhost:8080/api/game/player/{playerNumber}`|
|GET|`localhost:8080/api/game/player/pc/{playerNumber}`|
|GET|`localhost:8080/api/game/player/removeplayer`|
|GET|`localhost:8080/api/game/player/create_player`|
|GET|`localhost:8080/api/game/player/update_player`|
|GET|`localhost:8080/api/game/player/search?name={name}`|
|---|---|
|POST|`localhost:8080/api/game/player`|
|POST|`localhost:8080/api/game/player/savePlayer`|
|POST|`localhost:8080/api/game/player/updatePlayer`|
|---|---|
|DELETE|`localhost:8080/api/game/player/delete/{playerNumber}`|
|---|---|
|PUT|`localhost:8080/api/game/player/{playerNumber}`|

## Wiki

<a href="https://github.com/fajni/Game_Room/wiki">Link to <i>wiki</i> for more.</a>

<hr/>

Check out branches:

- `git checkout main`
- `git checkout only_api` - Only API part without login & registration backend,
- `git checkout login-registration-backend` - Only login & registration backend without API part.
