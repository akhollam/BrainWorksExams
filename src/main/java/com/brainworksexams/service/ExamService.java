package com.brainworksexams.service;

import java.util.List;

import com.brainworksexams.models.QuestionDto;

public interface ExamService {

	List<QuestionDto> listQuestions(Long customerId, String examCode);

}
