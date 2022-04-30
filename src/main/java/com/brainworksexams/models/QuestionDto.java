package com.brainworksexams.models;

import java.util.ArrayList;

import lombok.Data;

@Data
public class QuestionDto {

	private String text;
	private ArrayList<OptionsDto> options;

}
