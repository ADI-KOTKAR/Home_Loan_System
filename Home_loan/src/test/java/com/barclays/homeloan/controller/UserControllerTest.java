package com.barclays.homeloan.controller;

import com.barclays.homeloan.entity.User;
import com.barclays.homeloan.repository.UserRepository;
import com.barclays.homeloan.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    private User user;

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @BeforeAll
    public void setUp(){
        user = new User(1,"Ace","ace@gmail","pass");
    }

    @Test
    @DisplayName(value = "Find User By Id")
    public void testFindUserById(){
        when(userService.getUserById(1)).thenReturn(user);
        assertEquals(HttpStatus.OK,userController.findUserById(1).getStatusCode());
    }

    @Test
    @DisplayName(value = "Getting All Users")
    public void testFindAll(){
        when(userService.getAllUsers()).thenReturn(
                Stream.of(user).collect(Collectors.toList()));

        assertEquals(HttpStatus.OK,userController.findAll().getStatusCode());

    }

    @Test
    @DisplayName(value = "Adding a User")
    public void testAddAccount(){
        when(userService.addAccount(user)).thenReturn(user);

        assertEquals(HttpStatus.CREATED,userController.addAccount(user).getStatusCode());
    }
}
