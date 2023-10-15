# Login, Registration, Spring Security, Email Verification

<p align="center">
  <img src = "https://www.dariawan.com/media/images/tech-spring-security.width-1024.png" alt="Spring Security" width="40%" alt="Spring Security"/>
</p>
<hr/>

<h2>Contents: </h2>

- [Login, Registration, Spring Security, Email Verification](#login-registration-spring-security-email-verification)
  - [Intro](#intro)
  - [User registration](#user-registration)
  - [Registration process](#registration-process)
  - [Application user (roles: admin,user)](#application-user-roles-adminuser)
  - [Secure endpoints (spring security)](#secure-endpoints-spring-security)

Registration and User Login + Email Verification (Token Expires) + Encrypted Password

## Intro

Person wants to become user of our system, and after they complete a <i>user registration, verification links, email</i>
they become user with a <i>user role</i> in application. Person becomes user when the registration was successful. After registration goes login.

Once Person becomes Application User, they can before defined actions.

## User registration

<p align="center">
  POST HTTP Request from Person:
  <br/>
    <img alt="PostMan_example" src="https://github.com/fajni/Game_Room/assets/87950386/7d8ce351-c9e8-4d32-8210-009096b7c1cc" height="50%" width="50%"/>
</p>

<p align="center">
    Email that has been send:
    <br/>
    <img alt = "Email" src="https://github.com/fajni/Game_Room/assets/87950386/efb78b04-ba0e-4299-acf9-a187678f035a" height="50%" width="50%"/>
</p>

Verification link will expire in 15min.

Confirming Token from PostMan in order to become App User, (GET Request):
<pre>localhost:8080/api/game/registration/confirm?token=4df3538f-fc3c-4b7f-a121-1991d7d603d5</pre>

## Registration process

<img alt="Registration process" src="assets/Registration_process.png"/>

## Application user (roles: admin,user)

User should be able to only see available PCs, but Admin can do all.

## Secure endpoints (spring security)

All available endpoints are secured by spring security.

<hr/>

Clone full project: 
```bash
git clone https://github.com/fajni/Game_Room.git
```

_Note: In full project AppUserDetails class in removed. AppUser implements UserDetails_