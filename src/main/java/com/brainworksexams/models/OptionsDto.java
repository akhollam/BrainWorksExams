package com.brainworksexams.models;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OptionsDto {

	@NotNull
	private String answerText;
	
	@JsonProperty("correct_answer")
	private boolean correctAnswer;
}
