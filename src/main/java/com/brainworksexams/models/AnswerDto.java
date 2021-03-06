package com.brainworksexams.models;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AnswerDto {

	@NotEmpty
	private String text;
}
