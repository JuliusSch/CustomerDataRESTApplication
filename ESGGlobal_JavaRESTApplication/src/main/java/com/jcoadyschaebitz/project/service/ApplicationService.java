package com.jcoadyschaebitz.project.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jcoadyschaebitz.project.entity.Customer;
import com.jcoadyschaebitz.project.exceptions.InvalidCustomerFormatException;
import com.jcoadyschaebitz.project.utility.CSVParser;

public class ApplicationService {

	private CustomerService customerService;
	
	public ApplicationService() {
		customerService = new CustomerService();
	}
	
	public void runApplication() {
		Scanner scanner = new Scanner(System.in);
		boolean continueOperation = true;

		while (continueOperation) {
			System.out.println(
					"\nPlease enter 'post' to send customer data, 'get' to retrieve customer data or 'end' to finish operation:");
			String response = scanner.nextLine().toLowerCase();
			switch (response) {
			case "post":
				beginPostCustomers(scanner);
				break;
			case "get":
				beginGetCustomers(scanner);
				break;
			case "end":
				continueOperation = false;
				break;
			default:
				break;
			}
		}

		scanner.close();
	}
	
	public void beginPostCustomers(Scanner scanner) {
		File csvFile;
		List<Customer> customers = new ArrayList<Customer>();

		do {
			System.out.println("\nPlease enter a valid file directory (or leave blank for default):");

			String filePath = scanner.nextLine();

			if (filePath == "")
				filePath = System.getProperty("user.dir")
						+ "\\src\\main\\resources\\customers.csv";

			csvFile = new File(filePath);
		} while (!csvFile.exists() || !csvFile.isFile());

		try (Scanner fileScanner = new Scanner(csvFile)) {

			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				Customer customer = CSVParser.parseRow(line);

				customers.add(customer);
				System.out.println(customer.toString());
			}

			customerService.uploadCustomers(customers);

			System.out.println("Customers successfully uploaded.");

		} catch (FileNotFoundException e) {
			System.err.println("An error occurred while trying to read the file.");
			e.printStackTrace();
		} catch (InvalidCustomerFormatException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void beginGetCustomers(Scanner scanner) {
		List<Customer> customers = new ArrayList<Customer>();
		System.out.println("\nPlease enter a customer reference to get that customer's data (leave blank for all):");
		String response = scanner.nextLine();
		if (response.equals("")) {
			customers = customerService.getCustomers(null);
		} else {
			try {
				long ref = Long.parseLong(response);
				customers = customerService.getCustomers(ref);
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid customer reference.");
			}
		}
		if (customers.size() > 0) {
			for (Customer customer : customers) {
				System.out.println(customer.toString());
			}
		} else {
			System.out.println("No customers found.");
		}
	}

}
