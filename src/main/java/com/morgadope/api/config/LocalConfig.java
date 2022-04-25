package com.morgadope.api.config;

import com.morgadope.api.domain.User;
import com.morgadope.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB() {

        User u1 = new User(null, "Pedro", "pedro@mail.com", "123");
        User u2 = new User(null, "Morgado", "morgado@mail.com", "123");

        repository.saveAll(List.of(u1, u2));
    }
}
