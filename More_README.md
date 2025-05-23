<h1 align = "center"><img alt="SpringBoot" src="https://spring.io/img/logos/spring-initializr.svg" width=5% height=5%/>
<i>"Game Room"</i> Project <img alt="SpringBoot" src="https://spring.io/img/logos/spring-initializr.svg" width=5% height=5%/>
</h1>

Spring Boot MVC web application with Spring Boot Security. 
Manage PCs and Players.

This project is not made to look good visually, but to be functional.

# Content:

- [Prerequisites](#prerequisites)
- [Project](#project)
  - [Project Description](#project-description)
- [ER Diagrams](#er-diagrams)
  - [Login/Registration](#loginregistration)
- [Endpoints](#endpoints)
  - [Demo Controller](#democontroller)
  - [Pc Controller](#pccontroller)
  - [Player Controller](#playercontroller)
  - [Login/Registration Controller](#loginregistrationcontroller)
- [Authentication](#authentication)
  - [JWT - Stateless Authentication](#stateless-authentication)
  - [Username/Password - Stateful Authentication](#stateful-authentication)
- [To Do](#to-do)

# Prerequisites

- Spring Boot Version: 3.4.0
- Java SE 23,
- Dependencies:
  - Spring Boot JPA,
  - MySQL Driver,
  - Spring Web,
  - Thymeleaf,
  - Spring Security,
  - Java Mail Sender,
  - JWT (0.12.6):
    - JJWT :: API 0.12.6,
    - JJWT :: Impl 0.12.6,
    - JJWT :: Extensions :: Jackson 0.12.6
- Bootstrap

# Project

Images from final projects: 

<details>
<summary>IMAGES</summary>

- Landing page:

  <img src="./images/landing-page.png"/>
  <hr/>

- Login page:

  <img src="./images/login-page.png"/>
  <hr/>

- Registration page:

  <img src="./images/registration-page.png"/>
  <hr/>

- Home page:

  <img src="./images/home-page.png"/>
  <hr/>

- Employee PC View (_Same with Employee Player View_):

  <img src="./images/employee-pc.png"/>
  <hr/>

- Manager PC View (_Same with Manager Player View_):

  <img src="./images/manager-pc.png"/>
  <hr/>

- Admin PC View (_Same with Admin Player View_):

  <img src="./images/admin-pc.png"/>
  <hr/>

</details>

## Project Description

The "Game Room" project is designed to monitor the availability of each PC, its details and current player.
Provided tables present a list of available PCs, their Details and the Players currently using them.
Player can't be stored into database/table if he's not using the PC.

# ER Diagrams

<img src="./images/er.png" />

| Relationship     | Association    | Fetch Type | Description                                                                                                                            |
|------------------|----------------|------------|----------------------------------------------------------------------------------------------------------------------------------------|
| pcs - pc_details | Bi-directional | LAZY       | If pc deleted also delete his pc details. If pc details deleted, don't delete pc.<br/> If we save pc, also save his details if linked. |
| pcs - players    | Bi-directional | LAZY       | If player deleted, don't delete pc. If pc deleted also delete player.<br/> If we save player, don't save pc.                           |

## Login/Registration

<img src="./images/users-roles.png"/>

# Endpoints

## DemoController

| Request | Link                     | Description                                          |
|---------|--------------------------|------------------------------------------------------|
| GET     | localhost:8080           | Landing page.<br/>Everyone can see this page.        |
| GET     | localhost:8080/home      | Home page.<br/>Only logged users can see this page.  |
| GET     | localhost:8080/account   | Your account page. _(username, password, role, ...)_ |
| GET     | localhost:8080/accounts  | All registered available accounts and their roles.   |

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

| Request      | Link                                         | Description                                                                                                              |
|--------------|----------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| GET          | localhost:8080/players                       | Show all players.<br/>Every logged user can see.                                                                         |
| GET          | localhost:8080/players/{playerId}            | Show Details about player. <br/>Every logged user can see.                                                               |
| GET (DELETE) | localhost:8080/remove/player/{playerId}      | Delete player. <br/>Only Admins can delete player.<br/> Reason why delete with GET: html form can't send DELETE request. |
| DELETE       | localhost:8080/remove/player/{playerId}      | Delete player. <br/>Only Admins can send delete request.                                                                 |
| GET          | localhost:8080/save/player                   | Show form for ADDING a new player. <br/> Only Managers & Admins can see.                                                 |
| POST         | localhost:8080/save/player                   | Save player. <br/> Only Managers & Admins can save new player.                                                           |
| GET          | localhost:8080/update/player/{playerId}      | Show form for UPDATING the player.<br/>Only Managers & Admins can see.                                                   |
| PUT          | localhost:8080/update/player/{playerId}      | Update player.<br/>Only Managers & Admins can update player.                                                             |
| POST         | localhost:8080/form/update/player/{playerId} | Update player.<br/> Only Mangers & Admins can update player.<br/> Same reason with DELETE request.                       |

## LoginRegistrationController

| Request | Link                            | Description         |
|---------|---------------------------------|---------------------|
| GET     | localhost:8080/showLoginPage    | Login page.         |
| GET     | localhost:8080/showRegisterPage | Registration page.  |
| GET     | localhost:8080/access-denied    | Access Denied page. |
| POST    | localhost:8080/logout           | User logout.        |
| POST    | localhost:8080/register         | Add/Save new user.  |
| POST    | localhost:8080/myLogin          | User login.         |

# Authentication

## Stateless Authentication

__JWT TOKEN__

If stateless authentication is used, you can't use Thymeleaf views in Controllers.

Example (Postman):

<img src="./images/JWT_players.PNG"/>

## Stateful Authentication

__UsernamePasswordAuthenticationToken__

To enable stateful authentication again change next code:

- GameRoomSecurityConfig (disable _sessionManagement_ and _addFilterBefore_):
```java
@Configuration
public class GameRoomSecurityConfig {
    
    // ...

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                //  ...
                
                //.sessionManagement(session ->
                //        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                
                //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
```

- LoginRegistrationController (change _myLogin_ post request):
```java
@Controller
public class LoginRegistrationController {
    
    // ...

    @PostMapping("/myLogin")
    @ResponseBody
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
  
        if(!userService.verify(email, password, request)){
          return "redirect:/showLoginPage";
        }
        
        return "redirect:/home";
    }
}
```

# TO DO

- Custom Login/Logout = (01-Feb-2025), 
- JWT = (15-May-2025), 
- Spring Email = (13-May-2025), 
- Pagination, 