package com.brainworksexams.models;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuestionDto {

	@NotNull
	@NotEmpty
	private String text;
	
	@JsonProperty("global_question_code")
	private String globalQuestionCode;
	
	@NotNull
	private ArrayList<OptionsDto> options;
	
	private ArrayList<TagDto> tags;

}
