package com.barclays.homeloan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.barclays.homeloan.entity.LoanApplication;


public interface LoanApplicationService {
	
	LoanApplication addrequest(LoanApplication req);
	
	String validate(int id);
	List<LoanApplication> getAllLoan(); 
	
    LoanApplication getLoanApplicationById(int id);

}
