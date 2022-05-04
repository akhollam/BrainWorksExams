package com.brainworksexams.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brainworksexams.models.ExamAttemptCode;
import com.brainworksexams.models.ExamCode;
import com.brainworksexams.models.ExamRespDto;
import com.brainworksexams.models.UserDto;
import com.brainworksexams.models.UserRegDto;
import com.brainworksexams.service.ExamAttemptService;
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

	@Autowired
	private ExamAttemptService examAttemptService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createUser(@Valid @RequestBody UserRegDto userDto) {
		userService.createUser(userDto);
	}

	@GetMapping()
	@Operation(summary = "Get user information.")
	public UserDto getUser(Authentication authentication) {
		log.debug("getName - {}", authentication.getName());
		return userService.getUserInfo(authentication.getName());
	}

	@Operation(summary = "List all user registered exams.")
	@GetMapping("/list-exams")
	public List<ExamRespDto> listRegisteredExams(Authentication authentication) {
		return userService.listExams(authentication.getName());
	}

	@PostMapping("/register-exam")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerExam(Authentication authentication, @RequestBody ExamCode examCode) {
		userService.registerExam(authentication.getName(), examCode);
	}

	@PostMapping("/{exam_code}/start-exam")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ExamAttemptCode startExam(Authentication authentication, @RequestBody ExamCode examCode) {
		return examAttemptService.startExam(authentication.getName(), examCode.getExamCode());
	}

}
