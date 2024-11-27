<h1 align = "center"><img alt="SpringBoot" src="https://spring.io/img/logos/spring-initializr.svg" width=5% height=5%/>
<i>"Game Room"</i> Project <img alt="SpringBoot" src="https://spring.io/img/logos/spring-initializr.svg" width=5% height=5%/>
</h1>

# Content:

- [Project](#project)
- [ER Diagram](#er-diagram)
- [DAO Methods](#dao-methods)
  - [Pc methods](#pc-methods)
  - [Pc Detail methods](#pcdetail-methods)
  - [Player methods](#player-methods)
- [Images](#images)

# Project

- Spring Boot Version:
- Java Version:
- Dependencies:
  - Spring Boot JPA,
  - MySQL Driver,
  - Spring Web,
  - Thymeleaf,
  - Spring Security
- Bootstrap

# ER Diagram

<img src="./other/er.png" />

| Relationship     | Association    | Fetch Type | Description                                                                                                                            |
|------------------|----------------|------------|----------------------------------------------------------------------------------------------------------------------------------------|
| pcs - pc_details | Bi-directional | LAZY       | If pc deleted also delete his pc details. If pc details deleted, don't delete pc.<br/> If we save pc, also save his details if linked. |
| pcs - players    | Bi-directional | LAZY       | If player deleted, don't delete pc. If pc deleted also delete player.<br/> If we save player, don't save pc.                           |

# Endpoints

## DemoController

| Request | Link                 | Description                                         |
|---------|----------------------|-----------------------------------------------------|
| GET     | localhost:8080       | Landing page.<br/>Everyone can see this page.       |
| GET     | localhost:8080/home  | Home page.<br/>Only logged users can see this page. |

## PcController

| Request      | Link                                      | Description                                                                                                      |
|--------------|-------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| GET          | localhost:8080/pcs                        | Show all pcs.<br/>Every logged user can see.                                                                     |
| GET          | localhost:8080/pcs/{pcNumber}             | Show Details about pc. <br/>Every logged user can see.                                                           |
| GET (DELETE) | localhost:8080/remove/pc/{pcNumber}       | Delete pc. <br/>Only Admins can delete pc.<br/> Reason why delete with GET: html form can't send DELETE request. |
| DELETE       | localhost:8080/remove/pc/{pcNumber}       | Delete pc. <br/>Only Admins can send delete request.                                                             |
| GET          | localhost:8080/save/pc                    | Show form for ADDING a new pc. <br/> Only Managers & Admins can see.                                             |
| POST         | localhost:8080/save/pc                    | Save pc. <br/> Only Managers & Admins can save pc.                                                               |
| GET          | localhost:8080/update/pc/{pcNumber}       | Show form for UPDATING the pc.<br/>Only Managers & Admins can see.                                               |
| PUT          | localhost:8080/update/pc/{pcNumber}       | Update pc.<br/>Only Managers & Admins can update pc.                                                             |
| POST         | localhost:8080/form/update/pc/{pcNumber}  | Update pc.<br/> Only Mangers & Admins can update pc.<br/> Same reason with DELETE request.                       |

## PlayerController



# Images