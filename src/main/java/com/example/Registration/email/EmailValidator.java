package com.example.Registration.email;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator {

    public boolean validEmail(String email) {
        if (!email.contains("mail") || !email.contains("@"))
            throw new IllegalStateException("Email " + email + " not valid!");
        return true;
    }
}
