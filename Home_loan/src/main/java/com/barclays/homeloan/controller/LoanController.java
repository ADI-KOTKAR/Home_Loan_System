package com.barclays.homeloan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.homeloan.constants.SystemConstants;
import com.barclays.homeloan.repository.LoanRepository;
import com.barclays.homeloan.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	LoanService loanService;
	
	@GetMapping(value = SystemConstants.LOAN_BY_ID)
	public ResponseEntity<?> findLoanById(@PathVariable int id){
		try {
			logger.info("api running !!");
			return new ResponseEntity<>(loanService.getLoanById(id), HttpStatus.OK);
		}
		catch(Exception e){
			logger.error("Error occurred while fetching all Loan data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all Loan data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	

}
