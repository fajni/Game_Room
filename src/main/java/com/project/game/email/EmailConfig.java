package com.project.game.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    //Email Configuration For Email Server. Spring Boot requires @Bean type JavaMailSender, idk

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("your.real.email@gmail.com"); //real email
        javaMailSender.setPassword("1234 5678 9abc defg"); //real app password

        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");

        return javaMailSender;
    }
}
