package com.project.game.appuser;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode //equals()
@NoArgsConstructor
@AllArgsConstructor
@Entity //table
//application user itself
public class AppUser implements UserDetails {

    @SequenceGenerator(
            name="app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email; //userName
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole; //enum
    private Boolean locked = false; //da li je nalog zakljucan
    private Boolean enabled = false;

    public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString(){
        return "\nID: "+getId()+
                "\nFirst name: "+getFirstName()+
                "\nLast name: "+getLastName()+
                "\nEmail: "+getEmail()+
                "\nPassword: "+getPassword()+
                "\nRole: "+getAppUserRole()+
                "\nLocked: "+getLocked()+
                "\nEnabled: "+getEnabled();
    }
}
