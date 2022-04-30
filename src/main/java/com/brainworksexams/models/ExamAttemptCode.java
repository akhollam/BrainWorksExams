package com.brainworksexams.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExamAttemptCode {

	@JsonProperty("exam_attempt_code")
	private String examAttemptCode;
}
