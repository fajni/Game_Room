# Spring Boot RestApi <img alt="SpringBoot" src="https://spring.io/img/logos/spring-initializr.svg" width=5% height=5%/>

<p>Resp API Without Login, Security and Email Part. Basic API with HTML using Thymeleaf.</p> 

## Contents

- [Rest API Overview](#rest-api-overview)
- [Project Overview](#project-overview)
  - [Adding New Pc, Player](#adding-new-pc-player)
  - [Removing Pc, Player](#removing-pc-player)
  - [Updating Pc, Player](#updating-pc-player)
- [Preparations](#preparations)
- [Available Endpoints](#available-endpoints)
- [Postman Collection](#postman-collection)

## Rest API Overview

[![image](https://github.com/fajni/Game-Room/assets/87950386/f4fb6245-72a6-4025-88b8-115636da8ab4)](#)

<img align="right" src="https://github.com/fajni/Game-Room/assets/87950386/a29e316f-9e44-448f-b133-e04a064fd350" height="50%" width="50%"/>
<br/>
Rest API has layered architecture, there are many layers but the main ones are Controller, Service and Repository. 
Controller class is used for handling HTTP requests such as GET,  POST, DELETE, PUT. The Service Layer is for building business logic, methods that are available. 
Repository interface is placed between api and database. 
Main task of the repository is to enable connection with database and to read/store data in database.

## Project Overview

*Spring Boot Version*: 3.1.2

*Java Version*: 20

*Dependecies*: 
- Spring Data JPA,
- Spring Web,
- Postgre SQL Driver,
- Thymeleaf,
- Dotenv
  - *groupId: io.github,cdimascio,*
  - *artifactId: java-dotenv,*
  - *version: 5.2.2*

All the front-end is done by using [*Thymeleaf*](https://www.thymeleaf.org/) dependecy, simple HTML files with Bootstrap libraries. Main idea is to track which pc is free for use and also to see which pc is used by player in game room.

Example of endpoint:

>localhost:8080/api/game/pc

Endpoint ``/api/game/pc`` provides a table of PCs that are available in gameroom, their title, status(*which can only by ON or OFF*) and player using the pc.

![pcs](https://github.com/fajni/Game-Room/assets/87950386/5b29740e-aa4d-4bb7-b64b-28227bdce5c1)

Similary goes with ``/api/game/player``.

### Adding New Pc, Player

Adding a new pc is only possible if the provided pc number and title doesn’t currently exist in pc table. Also, the only necessary value needed for adding/creating a new pc is number, both title and status will be assigned automatically. *(Default value for status will be OFF but it is possible to set it on ON, and default title will be PC and then provided number for that pc)*. Therwise, an exception is raised.

Adding a new player goes the same way, but the fields for creating new player are **all required** because we don’t want to add a new player who doesn’t use a pc.

### Removing Pc, Player

Simple remove button in action column is for removing pc and player. It’s not possible to remove pc that already has a player. Removing a player will automatically set pc status on OFF and remove player number from a used pc, which enables that pc to be used again.

### Updating Pc, Player

For pc, it’s possible to update only title or status. For player, it’s possible to update name, lastname and pc number if it’s not occupied <sub>(pc doesn’t have a player number)</sub>.

## Preparations

Using [*Spring Initializr*](https://start.spring.io/) to set up Spring Boot project:

- Project: Maven
- Language: Java,
- Spring Boot: 3.1.3,
- Packaging: Jar,
- Java: 20

Installing Postgres:

[*PostgreSQL Server for Windows*](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

- Port: 5432,
- Locale: [Default locale],
- *It's not needed to install Stack Builder*.

Basic commands:

`\l` for available databases,

`\c` for connecting to database (example: `\c gameroom`),

`\d` for listing relations (example: `\d gameroom`)

Creating database for project (*from SQL Shell psql*):

<pre>CREATE DATABASE gameroom;

GRANT ALL PRIVILEGES ON DATABASE "[*playername*]" TO gameroom;</pre>

## Available Endpoints

***PcController***:
- Get requests:
  >localhost:8080/api/game/pc, localhost:8080/api/game/pc/
   
  >localhost:8080/api/game/pc/json

  >localhost:8080/api/game/pc/{pcNumber}

  >localhost:8080/api/game/pc/removePc

  >localhost:8080/api/game/pc/create_pc

  >localhost:8080/api/game/pc/update_pc

  >localhost:8080/api/game/pc/search?pcNumber={pcNumber}

- Post requests:
  >localhost:8080/api/game/pc

  >localhost:8080/api/game/pc/savePc

  >localhost:8080/api/game/pc/updatePc

- Delete request:
   >localhost:8080/api/game/pc/delete/{pcNumber}
- Put request:
   >localhost:8080/api/game/pc/{pcNumber}

***PlayerController***:
- Get requests:
  >localhost:8080/api/game/player, localhost:8080/api/game/player/

  >localhost:8080/api/game/player/json

  >localhost:8080/api/game/player/{playerNumber}

  >localhost:8080/api/game/player/pc/{playerNumber}

  >localhost:8080/api/game/player/removeplayer

  >localhost:8080/api/game/player/create_player

  >localhost:8080/api/game/player/update_player

  >localhost:8080/api/game/player/search?name={name}

- Post requests:
  >localhost:8080/api/game/player

  >localhost:8080/api/game/player/saveplayer

  >localhost:8080/api/game/player/updateplayer

- Delete request:
  >localhost:8080/api/game/player/delete/{playerNumber}

- Put request:
  >localhost:8080/api/game/player/{playerNumber}

## Postman Collection

It is possible to send direct HTTP request (POST, DEL, PUT, GET) using something like Postman.

<pre>
  {
	"info": {
		"_postman_id": "a64234c9-9b02-4ceb-8395-61aa6a4db7af",
		"name": "Game-api",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "28998626"
	},
	"item": [
		{
			"name": "Add new pc",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"pcNumber\": 5\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/game/pc",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"pc"
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete PC /delete/number",
			"request": {
				"method": "DELETE",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/game/pc/delete/4",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"pc",
						"delete",
						"4"
					]
				}
			},
			"response": []
		},
		{
			"name": "Update Pc (title, status)",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/game/pc/3?title=PC33&status=ON",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"pc",
						"3"
					],
					"query": [
						{
							"key": "title",
							"value": "PC33"
						},
						{
							"key": "status",
							"value": "ON"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Update Pc (title)",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/game/pc/3?title=PC3",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"pc",
						"3"
					],
					"query": [
						{
							"key": "title",
							"value": "PC3"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Update Pc (status)",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/game/pc/3?status=OFF",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"pc",
						"3"
					],
					"query": [
						{
							"key": "status",
							"value": "OFF"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Get PCs",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/game/pc",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"pc"
					]
				}
			},
			"response": []
		},
		{
			"name": "Add new player",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"playerNumber\": 2,\r\n    \"name\": \"Curtis James\",\r\n    \"lastname\": \"Jackson II\",\r\n    \"numberPc\": 2\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/game/player",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"player"
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete player",
			"request": {
				"method": "DELETE",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"playerNumber\": 1,\r\n    \"name\": \"Veljko\",\r\n    \"lastname\": \"Fajnisevic\",\r\n    \"pcNumber\": 1\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/game/player/delete/2",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"player",
						"delete",
						"2"
					]
				}
			},
			"response": []
		},
		{
			"name": "Get players",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/game/player",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"player"
					]
				}
			},
			"response": []
		},
		{
			"name": "Update player",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/game/player/1?lastname=Portant&name=John",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"game",
						"player",
						"1"
					],
					"query": [
						{
							"key": "lastname",
							"value": "Portant"
						},
						{
							"key": "pcNumber",
							"value": "9",
							"disabled": true
						},
						{
							"key": "name",
							"value": "John"
						}
					]
				}
			},
			"response": []
		}
	]
}
</pre>

[![image](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](#)
