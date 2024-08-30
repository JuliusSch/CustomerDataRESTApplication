package com.jcoadyschaebitz.project.utility;

import com.jcoadyschaebitz.project.entity.Customer;
import com.jcoadyschaebitz.project.exceptions.InvalidCustomerFormatException;

public class CSVParser {

	public static Customer parseRow(String line) throws InvalidCustomerFormatException {
		String[] values = line.split(",");
		if (values.length != 8)
			throw (new InvalidCustomerFormatException("Format Error: Incorrect number of values in row."));
		else {
			int customerRef;
			try {
				customerRef = Integer.parseInt(values[0]);
			} catch (NumberFormatException e) {
				throw (new InvalidCustomerFormatException("Format Error: Customer reference not a valid integer."));
			}
			Customer customer = new Customer(customerRef, values[1], values[2], values[3], values[4], values[5],
					values[6], values[7]);
			return customer;
		}
	}

}
