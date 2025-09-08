package com.gameroom.app.aspect;

/*

        SPRING AOP (AspectJ) - Aspect-Oriented Programming

        Define certain behaviors ("aspects") that cut
        across multiple classes, such as logging or
        transaction management.

        @Aspect - This class defines cross-cutting logic.
        @Component - Register this class as Spring Bean so it can actually be used at runtime

*/

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
// @Order(1)
public class GameRoomSecurityLoggingAspect {

    @Before("execution(public String login(..))")
    public void beforeUserLogin(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        String email = args[0].toString();
        String password = args[1].toString();

        System.out.println("AOP: \"" + email + "\" is trying to log in with \"" + password + "\" password!");
    }

    @AfterReturning(returning = "returnValue", value = "execution(public boolean verify(..))")
    public void afterUserVerify(JoinPoint joinPoint, Object returnValue) {

        Object[] args = joinPoint.getArgs();
        String email = args[0].toString();
        String password = args[1].toString();

        if( (boolean) returnValue) {
            System.out.println("AOP: User \"" + email + "\" is verified!");
        }
        else {
            System.err.println("AOP: User \"" + email + "\" could NOT BE verified!");
        }

    }

}
