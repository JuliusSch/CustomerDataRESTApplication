package com.jcoadyschaebitz.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.jcoadyschaebitz.project.entity.Customer;
import com.jcoadyschaebitz.project.repository.CustomerRepository;

@SpringBootTest
public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCustomers_returnsAllCustomers_whenCustomerRefUnspecified_verify() {
        Customer customer1 = new Customer();
        customer1.setCustomerRef(1L);
        Customer customer2 = new Customer();
        customer2.setCustomerRef(2L);
        Customer customer3 = new Customer();
        customer3.setCustomerRef(3L);
        
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));
        when(customerRepository.findById(2L)).thenReturn(Optional.of(customer2));
        when(customerRepository.findById(3L)).thenReturn(Optional.of(customer3));
        
        List<Customer> result = customerService.getCustomers(null);
        
        assertNotNull(result);
        assertEquals(customers, result);
    }
}