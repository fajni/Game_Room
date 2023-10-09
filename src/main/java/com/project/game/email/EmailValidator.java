package com.project.game.email;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator{

    public boolean validEmail(String mail){
        if(!mail.contains("@") && !mail.contains("mail"))
            throw new IllegalStateException("Email "+ mail + " is not valid!");
        return true;
    }
}
