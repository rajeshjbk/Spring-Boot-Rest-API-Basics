package com.raj.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.model.Company;
import com.raj.model.Customer;

@RestController
@RequestMapping("/customer-api")
public class CustomerOperationsController {

	@PostMapping("/register")
	public ResponseEntity<String> registerCompany(@RequestBody Company company) {
		
		System.out.println("Model class obj data:: "+company);
		
		//return ResponseEntity object
		return new ResponseEntity<String>(company.toString(), HttpStatus.OK);
	}
	
	@PostMapping("/register-cust")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
		
		System.out.println("Model class obj data:: "+customer);
		
		//return ResponseEntity object
		return new ResponseEntity<String>(customer.toString(), HttpStatus.OK);
	}
}
