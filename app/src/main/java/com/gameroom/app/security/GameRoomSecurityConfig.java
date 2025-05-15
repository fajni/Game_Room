package com.gameroom.app.security;

import com.gameroom.app.security.jwt.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity
public class GameRoomSecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // query to retrieve a user by email
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT u.email, u.password, true as enabled FROM users u WHERE u.email=?"
        );

        //query to retrieve the roles by email
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.email, r.role_name FROM users u, roles r WHERE u.email=? AND u.user_id=r.user_id"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http

                // for RestController (disable Cross Site Request Forgery):
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())

                // use HTTP Basic Authentication
                //.httpBasic(Customizer.withDefaults())
                .httpBasic(httpBasic -> httpBasic.disable())

                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/showLoginPage", "/showRegisterPage", "/users/**", "/register/**", "/", "/logout/**", "/myLogin/**", "/token/**").permitAll()

                                .requestMatchers("/home", "/pcs/**" , "/players/**", "/account/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")

                                .requestMatchers("/save/**", "/update/**", "/form/**", "/accounts/**").hasAnyRole("MANAGER", "ADMIN")

                                .requestMatchers("/remove/**").hasRole("ADMIN")

                                .anyRequest().authenticated()
                )

//                .formLogin(form ->
//                        form
//                                .loginPage("/showLoginPage")
//                                .usernameParameter("username")
//                                .passwordParameter("password")
//                                .loginProcessingUrl("/myLogin")
//                                .defaultSuccessUrl("/home")
//                                .permitAll()
//                )
                .formLogin(form -> form.disable()) // my custom login logic

//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                        .invalidateHttpSession(true) // delete session
//                        .deleteCookies("JSESSIONID") // delete session cookies
//                        .permitAll()
//                )
                .logout(logout -> logout.disable()) // my custom logout logic

                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied")
                )

                /* JWT TOKEN */
                // Spring Security won't create HTTP sessions for storing authentication state
                // Each request is completely independent and independently carries all the
                //      necessary data for authentication
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // jwt filter before username-password filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        ;

        return http.build();

    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//
//        return config.getAuthenticationManager();
//    }

}
