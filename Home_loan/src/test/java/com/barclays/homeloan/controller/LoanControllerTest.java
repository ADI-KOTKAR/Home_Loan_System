package com.barclays.homeloan.controller;

import com.barclays.homeloan.entity.Loan;
import com.barclays.homeloan.service.LoanService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoanControllerTest {

    @Mock
    LoanService loanService;

    @InjectMocks
    LoanController loanController;

    @Test
    @DisplayName(value = "Find Loan By Id")
    public void testFindLoanById(){
        when(loanService.getLoanById(1)).
                thenReturn(new Loan(1,200000,3,12,"Pending",2));

        assertEquals(HttpStatus.OK,loanController.findLoanById(1).getStatusCode());
    }

}
