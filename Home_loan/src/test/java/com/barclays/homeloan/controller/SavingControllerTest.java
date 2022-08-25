package com.barclays.homeloan.controller;

import com.barclays.homeloan.entity.SavingAccount;
import com.barclays.homeloan.service.SavingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SavingControllerTest {

    @Mock
    SavingService savingService;

    @InjectMocks
    SavingsController savingsController;

    private List<SavingAccount> savingAccountList;
    private SavingAccount savingAccount;

    @BeforeAll
    public void setUp(){
        savingAccountList = new ArrayList<>();
        Collections.addAll(savingAccountList,
                new SavingAccount(1,12345,"ace","ace@gmail",30000f),
                new SavingAccount(2,12342,"zoro","zoro@gmail",10000f),
                new SavingAccount(3,45644,"fire","fire@gmail",50000f));
        savingAccount = new SavingAccount(1,12345,"ace","ace@gmail",30000f);

    }

    @Test
    @DisplayName(value = "Get All Savings Account")
    public void testFindAll(){
        when(savingService.getAllAccounts()).thenReturn(savingAccountList);
        assertEquals(HttpStatus.OK,savingsController.findAll().getStatusCode());
    }

    @Test
    @DisplayName(value = "Saving Account")
    public void testAddAccount(){
        when(savingService.addAccount(any(SavingAccount.class))).thenReturn(savingAccount);
        assertEquals(HttpStatus.CREATED,savingsController.addAccount(savingAccount).getStatusCode());
    }

}
