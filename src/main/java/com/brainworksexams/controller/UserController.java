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

import com.brainworksexams.models.ExamAttemptCode;
import com.brainworksexams.models.ExamCode;
import com.brainworksexams.models.ExamDetails;
import com.brainworksexams.models.UserDto;
import com.brainworksexams.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "User API", description = "User & Exams Details.")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createUser(@RequestBody UserDto userDto) {
		userService.createUser(userDto);
	}

	@Operation(summary = "Get user information.")
	@GetMapping("/{user_code}")
	public UserDto getUser(@PathVariable("user_code") Long userId) {
		return null;
	}

	@Operation(summary = "List all user registered exams.")
	@GetMapping("/{user_code}/list-register-exams")
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<ExamCode> listRegisteredExams() {
		return null;
	}

	@PostMapping("/{user_code}/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerExam(@RequestBody ExamCode examCode) {

	}

	@GetMapping("/{user_code}/start/{exam_code}")
	public ExamDetails examDetails() {
		return new ExamDetails();
	}

	@PostMapping("/{user_code}/start/{exam_code}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ExamAttemptCode startExam(@RequestBody ExamCode examCode) {
		return new ExamAttemptCode();
	}

}
