package com.example.KomponentIntegrationTestExempel.service;

import com.example.KomponentIntegrationTestExempel.model.User;
import com.example.KomponentIntegrationTestExempel.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testgetUserByIdReturnsUser(){
        //arrange
        User user = new User(1L, "Bill","bill@mail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //act
        User result = userService.getUserById(1L).orElse(null);

        //assert
        assertEquals("Bill", result.getName());
        verify(userRepository).findById(1L);
    }

}