package com.brainworksexams.service;

import java.util.List;

import com.brainworksexams.models.CustomerDto;
import com.brainworksexams.models.ExamRespDto;

public interface CustomerService {

	public void createCustomer(CustomerDto customer);

	public ExamRespDto createExam(Long customerId, ExamRespDto exam);

	public List<ExamRespDto> listExams(Long customerId);

	public List<CustomerDto> listAllCustomers();

	public CustomerDto getCustomer(Long customerId);

}
