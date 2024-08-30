package com.jcoadyschaebitz.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcoadyschaebitz.project.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	List<Customer> findByCustomerRef(Long customerRef);
}