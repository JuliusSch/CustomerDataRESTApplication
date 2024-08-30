package com.jcoadyschaebitz.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcoadyschaebitz.project.entity.Customer;
import com.jcoadyschaebitz.project.repository.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository repository;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadCustomerData(@RequestBody Customer customerRecord) {
		repository.save(customerRecord);
		return ResponseEntity.ok("Customer saved successfully");
	}
	
	 @GetMapping("/get")
	    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(name = "customerRef", required = false) Long customerRef) {
		 List<Customer> customers;
		 
		 if (customerRef != null) {
	            customers = repository.findByCustomerRef(customerRef);
	        } else {
	            customers = repository.findAll();
	        }
	        
		 return ResponseEntity.ok(customers);
	    }
}
