package com.barclays.homeloan.service;


import com.barclays.homeloan.entity.User;
import com.barclays.homeloan.repository.UserRepository;
import com.barclays.homeloan.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    private List<User> userList;
    private User user;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;


    @BeforeAll
    public void setUp(){
        userList = new ArrayList<>();
        Collections.addAll(userList,
                new User(1, "Ace", "ace@gmail", "pass"),
                new User(2, "Zoro", "zoro@gmail", "pass2"),
                new User(3, "Fire", "fire@gmail", "pass3"));

        user = new User(1,"Ace","ace@gmail","pass");

    }

    @Test
    @DisplayName("Getting All User")
    public void testGetAllUser(){

        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(3,userServiceImpl.getAllUsers().size());

    }

    @Test
    @DisplayName(value = "Adding a user")
    public void testAddAccount(){

        when(userRepository.save(any(User.class))).thenReturn(user);
        assertEquals("Ace", userServiceImpl.addAccount(user).getName());

    }

    @Test
    @DisplayName(value = "Get User By Id")
    public void testGetUserById(){

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(userList.get(0)));
        when(userRepository.findById(2)).thenReturn(Optional.ofNullable(userList.get(1)));
        when(userRepository.findById(3)).thenReturn(Optional.ofNullable(userList.get(2)));

        assertAll("Testing getUserById",
                ()->assertEquals(1,userServiceImpl.getUserById(1).getId()),
                ()->assertEquals(2,userServiceImpl.getUserById(2).getId()),
                ()->assertEquals(3,userServiceImpl.getUserById(3).getId()));
    }

}
