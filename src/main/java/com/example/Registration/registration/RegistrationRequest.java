package com.example.Registration.registration;

public class RegistrationRequest {

    private final String first_name;
    private final String last_name;
    private final String password;
    private final String email;


    public RegistrationRequest(String firstName, String lastName, String password, String email) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.password = password;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "First name: " + first_name +
                "\nLast name: " + last_name +
                "\nPassword: " + password +
                "\nEmail: " + email;
    }
}
