package com.morgadope.api.service.impl;

import com.morgadope.api.domain.User;
import com.morgadope.api.domain.dto.UserDTO;
import com.morgadope.api.repositories.UserRepository;

import com.morgadope.api.service.exceptions.DataIntegratyViolationException;
import com.morgadope.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Pedro Morgado";
    public static final String EMAIL = "pedron.morgado@gmail.com";
    public static final String PASSWORD = "123456";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    @DisplayName("FindByID Test")
    void whenfindbyIdReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        User response = service.findbyId(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    @DisplayName("ObjectNotFoundByID Test")
    void whenFindByIdTheReturnReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            service.findbyId(ID);

        } catch (Exception exception) {

            assertEquals(ObjectNotFoundException.class, exception.getClass());
            assertEquals("Objeto não encontrado", exception.getMessage());
        }
    }

    @Test
    @DisplayName("FindAllUser Test")
    void whenFindAllTheReturnAlistOfUser() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    @DisplayName("CreateUser Test")
    void whenCreateReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    @DisplayName("EmailExists Test")
    void whenCreateReturnAnDataIntegratyViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {

            optionalUser.get().setId(2);
            service.create(userDTO);

        } catch (Exception exception) {

            assertEquals(DataIntegratyViolationException.class, exception.getClass());
            assertEquals("Email ja cadastrado no sistema", exception.getMessage());
        }


    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {

        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));

    }


}
