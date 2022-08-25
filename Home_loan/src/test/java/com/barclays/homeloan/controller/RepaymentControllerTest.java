package com.barclays.homeloan.controller;

import com.barclays.homeloan.entity.Loan;
import com.barclays.homeloan.entity.Repayment;
import com.barclays.homeloan.service.RepaymentService;
import com.barclays.homeloan.utils.TenureReqUtil;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RepaymentControllerTest {

    @InjectMocks
    RepaymentController repaymentController;

    @Mock
    RepaymentService repaymentService;

    private Loan loan;
    private Repayment repayment;
    private List<Repayment> repayList;

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
    @DisplayName(value = "Find Emi By Id")
    public void testFindEmiById(){
        when(repaymentService.getEmiById(1)).thenReturn(repayment);
        assertEquals(HttpStatus.OK,repaymentController.findEmiById(1).getStatusCode());
    }

    @Test
    @DisplayName(value = "Pay EMI ")
    public void testPayEmi(){
        when(repaymentService.payEmi(1)).thenReturn(repayment);
        assertEquals(HttpStatus.OK,repaymentController.payEmi(1).getStatusCode());
    }

    @Test
    @DisplayName(value = "For Pay EMI")
    public void testForPayEmi(){
        when(repaymentService.forPayEmi(1)).thenReturn("Foreclosed Successfully");
        assertEquals(HttpStatus.OK,repaymentController.forPayEmi(1).getStatusCode());
    }

    @Test
    @DisplayName(value = "Pre Pay EMI")
    public void testPrePayEmi(){
        when(repaymentService.prePayEmi(1,12)).thenReturn("Emi Paid succesfully");
        assertEquals(HttpStatus.OK,repaymentController.prePayEmi(1,new TenureReqUtil(12)).getStatusCode());
    }
}
