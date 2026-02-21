package com.raj.rest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.model.Company;
import com.raj.model.Customer;

@RestController
@RequestMapping("customer-api")
public class CustmerRestOperations {

	@GetMapping("/cust-report")
	public ResponseEntity<Customer> showCustomerData(){
		
		System.out.println("CustmerRestOperations.showCustomerData()");
		
		Customer cust = new Customer(101, "Raj", "Hyd", 8990.0);
		
		return new  ResponseEntity<Customer>(cust, HttpStatus.OK);
	}
	
	@GetMapping("/cust-all-report")
	public ResponseEntity<Customer> showCustomerAllData(){
		
		System.out.println("CustmerRestOperations.showCustomerAllData()");
		
		//collect the data
		Company company = new Company(110, "HCL", "Hyd", 5000);
		
		Customer cust = new Customer(102, "Ajit", "Hyd", 8990.0);
		cust.setFavColors(new String[] {"Red","Green","Yellow"});
		cust.setFriends(List.of("Ravi","Raj","Amit"));
		cust.setPhones(Set.of(8890987678L,3456789L,9876543654L));
		cust.setIdDetails(Map.of("aadhar",4567888,"voterId",976545D,"pan",56789));
		cust.setCompany(company);
		
		return new  ResponseEntity<Customer>(cust, HttpStatus.OK);
	}
	
	/*@GetMapping("/company-report-list")
	public ResponseEntity<List<Company>> showAllCompaniesReport(){
		
		System.out.println("CustmerRestOperations.showAllCompaniesReport()");
		Company comp1 = new Company(1001, "HCL", "Hyd", 100);
		Company comp2 = new Company(1002, "Wipro", "Vizag", 200);
		Company comp3 = new Company(1003, "CapGemini", "Pune", 500);
		
		List<Company> list = List.of(comp1,comp2, comp3);
		
		return new ResponseEntity<List<Company>>(list, HttpStatus.CREATED);
	}*/
	
	@GetMapping("/company-report-list")
	public List<Company> showAllCompaniesReport(){
		
		System.out.println("CustmerRestOperations.showAllCompaniesReport()");
		Company comp1 = new Company(1001, "HCL", "Hyd", 100);
		Company comp2 = new Company(1002, "Wipro", "Vizag", 200);
		Company comp3 = new Company(1003, "CapGemini", "Pune", 500);
		
		List<Company> list = List.of(comp1,comp2, comp3);
		
		return list;
	}
}
