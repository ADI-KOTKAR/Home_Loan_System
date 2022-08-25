package com.barclays.homeloan.service;

import com.barclays.homeloan.entity.Loan;
import com.barclays.homeloan.entity.Repayment;
import com.barclays.homeloan.repository.LoanRepository;
import com.barclays.homeloan.repository.RepaymentRepository;
import com.barclays.homeloan.repository.SavingRepository;
import com.barclays.homeloan.serviceimpl.RepaymentServiceImpl;
import com.barclays.homeloan.utils.EmiManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RepaymentServiceTest {

    private Repayment repayment;

    private Loan loan;

    private List<Repayment> repayList;

    @Mock
    RepaymentRepository repayRepo;

    @Mock
    SavingRepository savingRepository;

    @Mock
    LoanRepository loanRepository;

    @Mock
    EmiManager emiManager;

    @InjectMocks
    RepaymentServiceImpl repayServiceImpl;

    @BeforeAll
    public void setUp(){
        loan = new Loan(1,200000,3,12,"Pending",2);
        repayment = new Repayment(1, LocalDate.now(),5000.0f,100000f,0.07f,50000f,"Pending",loan);
        repayList = new ArrayList<>();
        Collections.addAll(repayList,
                new Repayment(1, LocalDate.now(),5000.0f,100000f,0.07f,50000f,"Pending",loan),
                new Repayment(2, LocalDate.now(),3000.0f,200000f,0.07f,70000f,"Pending",loan),
                new Repayment(3, LocalDate.now(),4000.0f,150000f,0.07f,60000f,"Pending",loan)
        );
    }

    @Test
    @DisplayName(value = "Get Emi By Id")
    public void testGetEmiById(){
        when(repayRepo.findById(1)).thenReturn(Optional.ofNullable(repayment));
        assertEquals(1,repayServiceImpl.getEmiById(1).getId());
    }

    @Test
    @DisplayName(value = "Get Emi By Loan Id")
    public void testGetEmiByLoanId(){
        when(loanRepository.findById(1)).thenReturn(Optional.ofNullable(loan));
        when(repayRepo.findByLoanId(loan)).thenReturn(repayList);
        assertEquals(3,repayServiceImpl.getEmiByLoanId(1).size());

    }
}
