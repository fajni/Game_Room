package com.project.game.security.config;

import com.project.game.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private final AppUserService appUserService;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/game/registration/**").permitAll(); //registration/**
                    auth.requestMatchers("/user").hasRole("USER");
                    auth.requestMatchers("/admin").hasRole("ADMIN");
                    auth.requestMatchers("/admin").hasAnyAuthority("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/api/game/sign_up")
                        //.loginPage("/api/game/login") //for html
                        .loginProcessingUrl("/api/game/login")
                        .defaultSuccessUrl("/api/game/player")
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }



    protected AuthenticationManagerBuilder configure(AuthenticationManagerBuilder auth) throws Exception{
        return auth.authenticationProvider(daoAuthenticationProvider());
    }

    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

}
