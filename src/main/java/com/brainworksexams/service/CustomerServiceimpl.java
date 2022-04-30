package com.brainworksexams.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainworksexams.entity.Customer;
import com.brainworksexams.entity.Exam;
import com.brainworksexams.models.CustomerDto;
import com.brainworksexams.models.ExamRespDto;
import com.brainworksexams.repository.CustomerRepository;
import com.brainworksexams.repository.ExamsRepository;
import com.brainworksexams.util.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceimpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ExamsRepository examsRepository;

	@Autowired
	private ModelMapper mapper;

	public void createCustomer(CustomerDto customer) {
		Customer cust = new Customer();
		cust.setName(customer.getName());
		log.info("Customer {} saved.", customer.getName());
		customerRepository.save(cust);
	}

	@Override
	public ExamRespDto createExam(Long customerId, ExamRespDto exam) {

		Optional<Customer> cust = customerRepository.findById(customerId);
		cust.ifPresent(c -> {
			Exam e = new Exam();
			e.setName(exam.getName());
			e.setDurationInMinutes(exam.getDurationInMinutes());
			e.setGlobalExamCode(Utility.uuid());
			e.setCustomer(c);
			examsRepository.save(e);
		});
		return null;
	}

	@Override
	public List<ExamRespDto> listExams(Long customerId) {
		Optional<Customer> cust = customerRepository.findById(customerId);
		if (cust.isPresent()) {
			Customer customer = cust.get();
			return customer.getExams().stream().map(ex -> mapper.map(ex, ExamRespDto.class))
					.collect(Collectors.toList());
		}
		return new ArrayList<ExamRespDto>();
	}

	@Override
	public List<CustomerDto> listAllCustomers() {
		return customerRepository.findAll().stream().map(cust -> mapper.map(cust, CustomerDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDto getCustomer(Long customerId) {
		Optional<Customer> cust = customerRepository.findById(customerId);
		if (cust.isPresent()) {
			return mapper.map(cust.get(), CustomerDto.class);
		}
		return null;
	}
}
