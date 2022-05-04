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

import com.brainworksexams.models.ExamDto;
import com.brainworksexams.models.ExamRespDto;
import com.brainworksexams.service.CustomerService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/questions")
@Tag(name = "Questions API", description = "Questions details. ")
public class QuestionsController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ExamRespDto createExam(@PathVariable("customer_id") Long customerId, @RequestBody ExamDto exam) {
		return null; //customerService.createQuestions();
	}
	
	@GetMapping("/exam")
	public List<ExamRespDto> listExams(@PathVariable("customer_id") Long customerId) {
			
		return null; //customerService.listExams(customerId);
	}
}
