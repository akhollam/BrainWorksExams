package com.brainworksexams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brainworksexams.models.ExamRespDto;
import com.brainworksexams.models.QuestionCode;
import com.brainworksexams.models.QuestionDto;
import com.brainworksexams.service.CustomerService;
import com.brainworksexams.service.ExamService;
import com.brainworksexams.service.QuestionsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer/{customer_id}")
@Tag(name = "Exam API", description = "Exams details. ")
public class ExamController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ExamService examService; 
	
	@Autowired
	private QuestionsService questionsService;

	@PostMapping("/exam")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ExamRespDto createExam(@PathVariable("customer_id") Long customerId, @RequestBody ExamRespDto exam) {
		
		log.debug("Creating exam - {}", exam.getName());
		return customerService.createExam(customerId, exam);
	}

	@GetMapping("/list-exams")
	public List<ExamRespDto> listExams(@PathVariable("customer_id") Long customerId) {
		return customerService.listExams(customerId);
	}

	@GetMapping("/exam/{exam_code}/list-questions")
	public List<QuestionDto> listExamQuestion(@PathVariable("customer_id") Long customerId,
			@PathVariable("exam_code") String examCode) {
		return examService.listQuestions(customerId, examCode);
	}

	@PostMapping("/exam/{exam_code}/map-question")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void mapExamQuestion(@PathVariable("customer_id") Long customerId,
			@PathVariable("exam_code") String examCode, @RequestBody QuestionCode questionCode) {
		questionsService.mapExamQuestion(customerId, examCode, questionCode);
	}

	@DeleteMapping("/exam/{exam_code}/question/{question_code}")
	public void deleteExamQuestion(@PathVariable("customer_id") Long customerId,
			@PathVariable("exam_code") String examCode, @PathVariable("question_code") String questionCode) {
		questionsService.deleteExamQuestion(customerId, examCode, questionCode);
	}

	@PostMapping("/exam/{exam_code}/question")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addExamQuestion(@PathVariable("customer_id") Long customerId,
			@PathVariable("exam_code") String examCode, @RequestBody QuestionDto questionDto) {
		questionsService.addExamQuestion(customerId, examCode, questionDto);
	}
}
