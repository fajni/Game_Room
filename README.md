<h1>Game Room Project</h1>

Spring Boot MVC web application with Spring Boot Security. 
Manage PCs and Players.

This project is not made to look good visually, but to be functional.

# Content:

- [Prerequisites](#prerequisites)
- [Project](#project)
  - [Project Description](#project-description)
- [ER Diagrams](#er-diagrams)
  - [Login/Registration](#loginregistration)
- [More about this project](#more-about-this-project)

# Prerequisites

- Spring Boot Version: 3.4.0
- Java SE 23,
- Dependencies:
  - Spring Boot JPA,
  - MySQL Driver,
  - Spring Web,
  - Thymeleaf,
  - Spring Security
- Bootstrap

# Project

Images from final projects:

<details>
<summary>IMAGES</summary>

  - Landing page:

  <img src="./other/landing-page.png"/>
  <hr/>

  - Login page:

  <img src="./other/login-page.png"/>
  <hr/>

  - Registration page:

  <img src="./other/registration-page.png"/>
  <hr/>

  - Home page:

  <img src="./other/home-page.png"/>
  <hr/>

  - Employee PC View (_Same with Employee Player View_):

  <img src="./other/employee-pc.png"/>
  <hr/>

  - Manager PC View (_Same with Manager Player View_):

  <img src="./other/manager-pc.png"/>
  <hr/>

  - Admin PC View (_Same with Admin Player View_):

  <img src="./other/admin-pc.png"/>
  <hr/>

</details>

## Project Description

The "Game Room" project is designed to monitor the availability of each PC, its details and current player.
Provided tables present a list of available PCs, their Details and the Players currently using them.
Player can't be stored into database/table if not using the PC.

# ER Diagrams

<img src="./other/er.png" />

## Login/Registration

<img src="./other/users-roles.png"/>

# More about this project

For more, check file: <a href = "./More_README.md">More_README.md</a>