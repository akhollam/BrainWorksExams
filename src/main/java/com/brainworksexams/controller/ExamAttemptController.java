package com.brainworksexams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainworksexams.exams.ExamQuestion;
import com.brainworksexams.exams.ExamQuestionAndAnswer;
import com.brainworksexams.exams.ExamResult;
import com.brainworksexams.models.QuestionCode;
import com.brainworksexams.service.ExamAttemptService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/exam-attempt/{exam_appempt_code}")
@Tag(name = "Exam Attempt API", description = "Exam Attempt details. ")
public class ExamAttemptController {

	@Autowired
	private ExamAttemptService examAttemptService;

	@PostMapping("/answer-question")
	public ExamQuestion answerQuestion(@PathVariable("exam_appempt_code") String attemptCode,@RequestBody ExamQuestionAndAnswer qna) {
		return examAttemptService.answerQuestion(attemptCode, qna );
	}

	@PostMapping("/next-question")
	public ExamQuestion nextQuestion(@PathVariable("exam_appempt_code") String attemptCode) {
		return examAttemptService.nextQuestion(attemptCode);
	}

	@PostMapping("/attempt-later")
	public ExamQuestion attemptLater(@PathVariable("exam_appempt_code") String attemptCode,
			@RequestBody QuestionCode code) {
		return examAttemptService.attemptLater(attemptCode, code.getQuestionCode());
	}

	@PostMapping("/finish")
	public ExamResult finish(@PathVariable("exam_appempt_code") String attemptCode) {
		return examAttemptService.finish(attemptCode);
	}

}
