package com.brainworksexams.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuestionCode {

	@JsonProperty("question_code")
	String questionCode;

}
