package com.project.game.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {

        if (!s.contains("@") && !s.contains("mail"))
            throw new IllegalStateException("Email " + s + " is not valid!");

        return true;
    }

    public void validEmail(String mail){
        if(!mail.contains("@") && !mail.contains("mail"))
            throw new IllegalStateException("Email "+ mail + " is not valid!");
    }
}
