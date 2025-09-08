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
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmailServiceAspect {

    @AfterReturning(returning = "returnValue", value = "execution(public boolean sendMessage(..))")
    public void emailSend(JoinPoint joinPoint, boolean returnValue) {

        Object[] args = joinPoint.getArgs();
        String to = args[0].toString();
        String subject = args[1].toString();
        String text = args[2].toString();

        if(returnValue) {
            System.out.println("AOP: Email has been sent to \"" + to + "\" with subject: \"" + subject + "\"");
        }
        else {
            System.err.println("AOP: Email couldn't be send to \"" + to + "\"");
        }

    }
}
