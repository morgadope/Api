package com.morgadope.api.resources;

import com.morgadope.api.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResourse {

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable InternalError id){

        return ResponseEntity.ok().body(new User(1,"pedro", "pedron.morgado@gmail.com", "2212"));

    }
}
