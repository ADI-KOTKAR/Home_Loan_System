package com.barclays.homeloan.service;

import com.barclays.homeloan.entity.SavingAccount;
import com.barclays.homeloan.repository.SavingRepository;
import com.barclays.homeloan.serviceimpl.SavingServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SavingServiceTest {

    private List<SavingAccount> savingAccountList;

    private SavingAccount savingAccount;

    @InjectMocks
    SavingServiceImpl savingServiceImpl;

    @Mock
    SavingRepository savingRepository;

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
    @DisplayName(value = "Getting All saving Accounts")
    public void testGetAllAccounts(){
        when(savingRepository.findAll()).thenReturn(savingAccountList);
        assertEquals(3,savingServiceImpl.getAllAccounts().size());
    }

    @Test
    @DisplayName(value = "Adding a Saving Account")
    public void testAddAccount(){
        when(savingRepository.save(any(SavingAccount.class))).thenReturn(savingAccount);
        assertEquals("ace",savingServiceImpl.addAccount(savingAccount).getName());
    }
}
