package com.brainworksexams.models;

import lombok.Data;

@Data
public class ExamRespDto {

	private String name;

	private String globalExamCode;

	private short durationInMinutes;

}
