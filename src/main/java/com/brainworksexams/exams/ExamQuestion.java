package com.brainworksexams.exams;

import java.util.List;

import lombok.Data;

@Data
public class ExamQuestion {

	private String questionText;
	
	private String questionCode;

	private List<ExamQuestionOption> option;

	private boolean attempted;

	private Long selectedAnswer;

	private boolean checkLater;

}
