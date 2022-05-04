package com.brainworksexams.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExamAttemptCode {

	public ExamAttemptCode(String examAttemptCode) {
		this.examAttemptCode = examAttemptCode;
	}

	@JsonProperty("exam_attempt_code")
	private String examAttemptCode;
}
