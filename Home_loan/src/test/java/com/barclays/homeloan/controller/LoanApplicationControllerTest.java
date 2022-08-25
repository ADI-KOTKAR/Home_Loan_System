package com.barclays.homeloan.controller;

import com.barclays.homeloan.entity.Loan;
import com.barclays.homeloan.entity.LoanApplication;
import com.barclays.homeloan.entity.Repayment;
import com.barclays.homeloan.entity.SavingAccount;
import com.barclays.homeloan.service.LoanApplicationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoanApplicationControllerTest {

    @Mock
    LoanApplicationService loanApplicationService;

    @InjectMocks
    LoanApplicationController loanApplicationController;

    private List<LoanApplication> loanApp;
    private SavingAccount savingAccount;
    private Loan loan;
    private Repayment repayment;

    @BeforeAll
    public void setUp(){
        loanApp = new ArrayList<>();
        Collections.addAll(loanApp,
                new LoanApplication(1,"Dehradun",100000,12,30000,"ace@gamil.com","Pending",2),
                new LoanApplication(2,"Dehradun",1000000,12,3000,"ace2@gamil.com","Pending",3)
        );
        savingAccount = new SavingAccount(1,12345,"ace","ace@gmail",30000f);
        loan = new Loan(1,200000,3,12,"Pending",2);
        repayment = new Repayment(1, LocalDate.now(),5000.0f,100000f,0.07f,50000f,"Pending",loan);
    }

    @Test
    @DisplayName(value = "Apply for Home Loan")
    public void testApplyHomeLoan(){
        when(loanApplicationService.addrequest(any(LoanApplication.class))).
                thenReturn(loanApp.get(0));
        assertEquals(HttpStatus.CREATED,loanApplicationController.applyHomeLoan(loanApp.get(0)).getStatusCode());
    }

    @Test
    @DisplayName(value = "Validate Loan Application")
    public void testValidateApplication(){
        when(loanApplicationService.validate(1)).thenReturn(any(String.class));
        assertEquals(HttpStatus.BAD_REQUEST,loanApplicationController.validateApplication(1).getStatusCode());
    }

    @Test
    @DisplayName(value = "Get All Loan Applications")
    public void testFindAll(){
        when(loanApplicationService.getAllLoan()).thenReturn(loanApp);
        assertEquals(HttpStatus.OK,loanApplicationController.findAll().getStatusCode());
    }

    @Test
    @DisplayName(value = "Loan Applications By Id")
    public void testloanApplication(){
        when(loanApplicationService.getLoanApplicationById(1)).
                thenReturn(any(LoanApplication.class));
        assertEquals(HttpStatus.OK,loanApplicationController.loanApplication(1).getStatusCode());
    }
}
