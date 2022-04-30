package com.brainworksexams.service;

import com.brainworksexams.models.QuestionCode;

public interface QuestionsService {

	void mapExamQuestion(Long customerId, String examCode, QuestionCode questionCode);


}
