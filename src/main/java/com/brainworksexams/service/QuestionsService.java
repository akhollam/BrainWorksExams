package com.brainworksexams.service;

import com.brainworksexams.models.QuestionCode;
import com.brainworksexams.models.QuestionDto;

public interface QuestionsService {

	void mapExamQuestion(Long customerId, String examCode, QuestionCode questionCode);

	void addExamQuestion(Long customerId, String examCode, QuestionDto questionDto);

	void deleteExamQuestion(Long customerId, String examCode, String questionCode);


}
