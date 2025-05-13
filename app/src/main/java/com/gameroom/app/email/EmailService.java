package com.gameroom.app.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public boolean sendMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();

        //message.setFrom("");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try{
            emailSender.send(message);
            return true;

        } catch (Exception e) {
            System.err.println("Couldn't send message - " + e.getMessage());
            return false;
        }
    }

}
