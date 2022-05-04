package com.brainworksexams.models;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class QuestionDto {

	@NotNull
	@NotEmpty
	private String text;
	
	@NotNull
	private ArrayList<OptionsDto> options;
	
	private ArrayList<TagDto> tags;

}
