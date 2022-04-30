package com.brainworksexams.controller;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserExams API", description = "User and exams details. ")
public class ExamAttemptController {

	@GetMapping("/{user_id}/{exam_code}/{exam_appempt_code}")
	public void name() {
		
	}
	
}
