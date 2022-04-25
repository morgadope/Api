package com.morgadope.api.service;

import com.morgadope.api.domain.User;
import com.morgadope.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findbyId(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
    User update(UserDTO obj);
}
