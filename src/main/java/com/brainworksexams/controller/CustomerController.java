package com.brainworksexams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brainworksexams.models.CustomerDto;
import com.brainworksexams.service.CustomerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer")
@Tag(name = "Customers API", description = "Customer & account details. ")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createCustomer(@RequestBody CustomerDto customer) {
		log.debug("Creating customer - {}", customer.getName());
		customerService.createCustomer(customer);
	}

	@GetMapping("/list-customer")
	private List<CustomerDto> listAllCustomers() {
		return customerService.listAllCustomers();
	}

	@GetMapping("/{customer_id}")
	private CustomerDto getCustomer(@PathVariable("customer_id") Long customerId) {
		return customerService.getCustomer(customerId);
	}
}
