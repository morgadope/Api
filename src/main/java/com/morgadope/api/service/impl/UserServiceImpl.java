package com.morgadope.api.service.impl;

import com.morgadope.api.domain.User;
import com.morgadope.api.domain.dto.UserDTO;
import com.morgadope.api.repositories.UserRepository;
import com.morgadope.api.service.UserService;
import com.morgadope.api.service.exceptions.DataIntegratyViolationException;
import com.morgadope.api.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findbyId(Integer id) {
        Optional<User> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<User> findAll() {

        return repository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        return repository.save(mapper.map(obj, User.class));
    }

    private void findByEmail(UserDTO obj) {

        if(repository.findByEmail(obj.getEmail()).isPresent()){
            throw new DataIntegratyViolationException("Email ja cadastrado no sistema");

        }
    }
}
