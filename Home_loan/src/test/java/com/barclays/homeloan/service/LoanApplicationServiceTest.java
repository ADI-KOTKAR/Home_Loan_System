package com.barclays.homeloan.service;

import com.barclays.homeloan.emailsender.EmailSenderService;
import com.barclays.homeloan.entity.Loan;
import com.barclays.homeloan.entity.LoanApplication;
import com.barclays.homeloan.entity.Repayment;
import com.barclays.homeloan.entity.SavingAccount;
import com.barclays.homeloan.repository.LoanApplicationRepository;
import com.barclays.homeloan.repository.LoanRepository;
import com.barclays.homeloan.repository.RepaymentRepository;
import com.barclays.homeloan.repository.SavingRepository;
import com.barclays.homeloan.service.LoanApplicationService;
import com.barclays.homeloan.serviceimpl.LoanApplicationServiceImpl;
import com.barclays.homeloan.utils.EmiManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoanApplicationServiceTest {

    private List<LoanApplication> loanApp;

    private SavingAccount savingAccount;

    private Loan loan;

    private Repayment repayment;

    @Mock
    LoanApplicationRepository loanApplicationRepository;

    @Mock
    LoanRepository loanRepository;

    @Mock
    SavingRepository savingRepository;

    @Mock
    RepaymentRepository repayRepository;


    @Mock
    EmailSenderService emailSenderService;

    @Mock
    EmiManager emiManager;

    @InjectMocks
    LoanApplicationServiceImpl loanApplicationServiceImpl;

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
    @DisplayName(value = "Loan Application submition")
    public void testAddRequest(){
        when(loanApplicationRepository.save(any(LoanApplication.class))).thenReturn(loanApp.get(0));
        assertEquals(1,loanApplicationServiceImpl.addrequest(loanApp.get(0)).getId());
    }

    @Nested
    @DisplayName(value = "Testing Validation for")
    class Validate{

        @Test
        @DisplayName(value = "Successfull Loan Application")
        public void testValidateSuccess(){
            when(loanApplicationRepository.findById(1)).thenReturn(Optional.ofNullable(loanApp.get(0)));
            when(loanApplicationRepository.save(any(LoanApplication.class))).thenReturn(loanApp.get(0));
            lenient().doNothing().when(emailSenderService).sendEmail("","","");

            when(savingRepository.findByEmail(loanApp.get(0).getEmail())).thenReturn(savingAccount);
            when(loanRepository.save(any(Loan.class))).thenReturn(loan);
            when(repayRepository.save(any(Repayment.class))).thenReturn(repayment);

            assertEquals("Loan Approved Successfully !!",loanApplicationServiceImpl.validate(1));
        }

        @Test
        @DisplayName(value = "Unsuccessfull Loan Application")
        public void testValidateUnSuccess(){
            when(loanApplicationRepository.findById(2)).thenReturn(Optional.ofNullable(loanApp.get(1)));
            when(loanApplicationRepository.save(any(LoanApplication.class))).thenReturn(loanApp.get(1));
            lenient().doNothing().when(emailSenderService).sendEmail("","","");

            assertEquals("Declined: loan should be less than monthlyincome*50",loanApplicationServiceImpl.validate(2));
        }

    }

    @Test
    @DisplayName(value = "Getting all LoanApplications")
    public void testGetAllLoan(){
        when(loanApplicationRepository.findAll()).thenReturn(loanApp);
        assertEquals(2,loanApplicationServiceImpl.getAllLoan().size());
    }

    @Test
    @DisplayName(value = "Get Loan Application by Id")
    public void testGetLoanApplicationById(){
        when(loanApplicationRepository.findById(1)).thenReturn(Optional.ofNullable(loanApp.get(0)));
        assertEquals(1,loanApplicationServiceImpl.getLoanApplicationById(1).getId());
    }
}
