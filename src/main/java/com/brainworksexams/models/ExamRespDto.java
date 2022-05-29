package com.brainworksexams.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExamRespDto {

	private String name;

	@JsonProperty("global_exam_code")
	private String globalExamCode;

	@JsonProperty("duration_in_minutes")
	private short durationInMinutes;
	
	private String description;
	
	private boolean publish;
	
	@JsonProperty("created_datetime")
	@JsonFormat(pattern = "dd/MM/yyyy HH:ss")
	private LocalDateTime createdDatetime;

}
