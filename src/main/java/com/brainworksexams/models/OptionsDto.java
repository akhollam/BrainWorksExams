package com.brainworksexams.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OptionsDto {

	private AnswerDto answer;
	
	@JsonProperty("correct_answer")
	private boolean correctAnswer;
}
