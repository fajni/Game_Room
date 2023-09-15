package com.project.game.security;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(){
        Dotenv dotenv = Dotenv.configure().load();
        String user = dotenv.get("POSTGRES_USER");
        String password = dotenv.get("POSTGRES_PASSWORD");
        String url = dotenv.get("POSTGRES_URL");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }
}
