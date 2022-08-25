package com.barclays.homeloan.service;

import com.barclays.homeloan.entity.Loan;
import com.barclays.homeloan.repository.LoanRepository;
import com.barclays.homeloan.serviceimpl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoanServiceTest {

    private Loan loan;

    @Mock
    LoanRepository loanRepository;

    @InjectMocks
    LoanServiceImpl loanServiceImpl;

    @BeforeAll
    public void setUp(){
        loan = new Loan(1,200000,3,12,"Pending",2);
    }

    @Test
    @DisplayName("Testing testGetLoan by Id")
    public void testGetLoanById(){
        when(loanRepository.findById(1)).thenReturn(Optional.ofNullable(loan));
        assertEquals(1,loanServiceImpl.getLoanById(1).getId());
    }
}

