package com.brainworksexams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	
	@Autowired
	private Environment environment;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createCustomer(@RequestBody CustomerDto customer) {
		log.debug("Creating customer - {}", customer.getName());
		customerService.createCustomer(customer);
	}

	@GetMapping("/list-customer")
	public List<CustomerDto> listAllCustomers() {
		return customerService.listAllCustomers();
	}

	@GetMapping("/{customer_id}")
	public CustomerDto getCustomer(@PathVariable("customer_id") Long customerId) {
		CustomerDto dto = customerService.getCustomer(customerId);
		dto.setPort(environment.getProperty("server.port"));
		return dto;
	}
}
