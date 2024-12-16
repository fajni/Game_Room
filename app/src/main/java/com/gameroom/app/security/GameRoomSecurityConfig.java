package com.gameroom.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class GameRoomSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT u.username, u.password, true as enabled FROM users u WHERE u.username=?"
        );

        //query to retrieve the roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.username, r.role_name FROM users u, roles r WHERE u.username=? AND u.user_id=r.user_id"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http

                // for RestController (disable Cross Site Request Forgery):
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())

                // use HTTP Basic Authentication
                .httpBasic(Customizer.withDefaults())

                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/showLoginPage", "/showRegisterPage", "/users/**", "/register/**", "/", "/logout").permitAll()

                                .requestMatchers("/home", "/pcs/**" , "/players/**", "/account/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")

                                .requestMatchers("/save/**", "/update/**", "/form/**").hasAnyRole("MANAGER", "ADMIN")

                                .requestMatchers("/remove/**").hasRole("ADMIN")

                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showLoginPage")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/home")
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied")
                );

        return http.build();

    }

}
