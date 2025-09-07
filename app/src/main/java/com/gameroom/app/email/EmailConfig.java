package com.gameroom.app.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/*

        Class that configures the email from which
        emails will be sent.

*/

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");

        // real email Username and Password - (check env.txt file)
        javaMailSender.setUsername("your.real.gmail@gmail.com");
        javaMailSender.setPassword("1234 5678 9abc defg"); // for password you need to add this app to gmail (https://myaccount.google.com/apppasswords)

        return javaMailSender;
    }
}
