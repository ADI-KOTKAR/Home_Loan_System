package com.barclays.homeloan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.barclays.homeloan.auth.MyUserDetailsService;
import com.barclays.homeloan.constants.SystemConstants;
import com.barclays.homeloan.entity.User;
import com.barclays.homeloan.repository.UserRepository;
import com.barclays.homeloan.service.UserService;
//import com.barclays.homeloan.utils.JwtReq;
//import com.barclays.homeloan.utils.JwtUtil;
//import com.barclays.homeloan.utils.JwtResponse;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
//	@Autowired
//	AuthenticationManager authenticationManager;
	
//	@Autowired
//	MyUserDetailsService myUserDetailsService;
	
//	@Autowired
//	JwtUtil jwtUtil;
	
//	@PostMapping(value = SystemConstants.GET_TOKEN)
//	public ResponseEntity<?> getToken(@RequestBody JwtReq jwtRequest){
//		try {
//			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//			
//		}
//		catch(UsernameNotFoundException e) {
//			logger.error(e.getMessage());
//			return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
//			
//			
//		}
//		catch(BadCredentialsException e) {
//			logger.error(e.getMessage());
//			return new ResponseEntity<>("Bad Credentials", HttpStatus.BAD_REQUEST);
//		}
//		catch(Exception e){
//			logger.error("Error occurred while fetching all users data: " + e.getMessage());
//            return new ResponseEntity<>("Error occurred while fetching all users data", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
//		String token = this.jwtUtil.generateToken(userDetails);
//		logger.info("token : " + token);
//		return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
//		
//	}
//	

	@GetMapping(value = SystemConstants.GET_USER_BY_ID)
	public ResponseEntity<?> findUserById(@PathVariable int id){
		try {
			logger.info("api running !!");
			return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
		}
		catch(Exception e){
			logger.error("Error occurred while fetching all User data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all Loan data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = SystemConstants.GET_ALL_USER)
	public ResponseEntity<?> findAll(){
		try {
			logger.info("api running !!");
			return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
		}
		catch(Exception e){
			logger.error("Error occurred while fetching all users data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all users data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = SystemConstants.ADD_USER)
	public ResponseEntity<?> addAccount(@RequestBody User user) {
		try {
			return new ResponseEntity<>(userService.addAccount(user), HttpStatus.CREATED);
		}
		catch(Exception e){
			logger.error("Error occurred while adding Account: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while adding Account", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
