package com.example.KomponentIntegrationTestExempel.service;

import com.example.KomponentIntegrationTestExempel.model.User;
import com.example.KomponentIntegrationTestExempel.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
public class UserServiceCompononentTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateAndFetchUser(){
        //arrange
        User user = new User(3L, "Bill","bill@mail.com");

        //act
        User savedUser = userService.createUser(user);
        User fetchedUser = userService.getUserById(savedUser.getId()).orElse(null);

        //assert
        assertEquals("Bill", fetchedUser.getName());
    }
}


