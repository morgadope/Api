package com.morgadope.api;

import com.morgadope.api.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PatchMapping;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    User user = new User(1,"Pedro","pedron.morgado@gmail.com", "250397");
}

