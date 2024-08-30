package com.jcoadyschaebitz.project.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcoadyschaebitz.project.entity.Customer;

public class CustomerService {
	
	private RestTemplate restTemplate;
	private ObjectMapper objectMapper;
	
	public static String API_URL = "http://localhost:8080";

	public RestTemplate getRestTemplate() {
		return (restTemplate == null) ? (restTemplate = new RestTemplate()) : restTemplate;
	}
	
	public ObjectMapper getObjectMapper() {
		return (objectMapper == null) ? (objectMapper = new ObjectMapper()) : objectMapper;
	}
	
	public List<Customer> getCustomers(Long customerRef) {
		String apiUrl = API_URL + "/get";
		if (customerRef != null) {
            apiUrl += "?customerRef=" + customerRef;
        }
		ResponseEntity<Customer[]> response = getRestTemplate().getForEntity(apiUrl, Customer[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return Arrays.asList(response.getBody());
		} else {
			System.out.println("Failed to retrieve records. Status code: " + response.getStatusCode());
			return null;
		}
	}
	
	public void uploadCustomers(List<Customer> customers) {
		for (Customer customer : customers) {
			String jsonCustomerRecord = convertToJSON(customer);

			sendPostRequest(jsonCustomerRecord);
		}
	}

	private String convertToJSON(Customer customer) {
		try {
			return getObjectMapper().writeValueAsString(customer);
		} catch (JsonProcessingException e) {
			System.err.println("Error in JSON conversion.");
			e.printStackTrace();
		}
		return null;
	}

	private void sendPostRequest(String customerJSONRecord) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<String> request = new HttpEntity<>(customerJSONRecord, headers);

		ResponseEntity<String> response = getRestTemplate().exchange(API_URL + "/upload",
				HttpMethod.POST, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Customer record uploaded successfully.");
		} else {
			System.out.println("Failed to upload record.");
		}
	}
}
