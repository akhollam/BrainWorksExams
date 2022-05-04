package com.brainworksexams.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExamCode {

	@JsonProperty("exam_code")
	private String examCode;

}
