package com.example.Registration.appuser;

import jakarta.persistence.*;

@Entity(name = "app_user")
public class AppUser{

    @Id
    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Boolean enabled = false; //true is when app user confirms token/email
    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    public AppUser() {
    }

    public AppUser(String first_name, String last_name, String email, String password, AppUserRole role) {
        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AppUserRole getRole() {
        return role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(AppUserRole role) {
        this.role = role;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String toString() {
        return  "Id: " + id +
                "\nFirst name: " + first_name +
                "\nLast name: " + last_name +
                "\nEmail: " + email +
                "\nPassword: " + password +
                "\nRole: " + role +
                "\nEnabled: " + enabled;
    }
}
