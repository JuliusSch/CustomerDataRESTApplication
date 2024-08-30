package com.jcoadyschaebitz.project.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Customer {

	@Id
	private long customerRef;
	private String customerName;
	private String addressLine1, addressLine2;
	private String town, county, country, postCode;
	
	public Customer() {
		setCustomerRef(0);
		setCustomerName("");
		setAddressLine1("");
		setAddressLine2("");
		setTown("");
		setCounty("");
		setCountry("");
		setPostCode("");
	}
	
	public Customer(long customerRef, String customerName, String addressLine1, String addressLine2, String town, String county, String country, String postCode) {
		setCustomerRef(customerRef);
		setCustomerName(customerName);
		setAddressLine1(addressLine1);
		setAddressLine2(addressLine2);
		setTown(town);
		setCounty(county);
		setCountry(country);
		setPostCode(postCode);
	}
	
	public long getCustomerRef() {
		return customerRef;
	}
	
	public void setCustomerRef(long customerRef) {
		this.customerRef = customerRef;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return addressLine2;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	
	public String getCounty() {
		return county;
	}
	
	public void setCounty(String county) {
		this.county = county;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Override
    public String toString() {
		return String.format("Customer: %d, %s, %s, %s, %s, %s, %s, %s", customerRef, customerName, addressLine1, addressLine2, town, county, country, postCode);
	}
	
}
