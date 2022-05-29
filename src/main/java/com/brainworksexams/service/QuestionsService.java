package com.brainworksexams.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.brainworksexams.models.QuestionCode;
import com.brainworksexams.models.QuestionDto;

public interface QuestionsService {

	void mapExamQuestion(Long customerId, String examCode, QuestionCode questionCode);

	void addExamQuestion(Long customerId, String examCode, QuestionDto questionDto);

	void deleteExamQuestion(Long customerId, String examCode, String questionCode);

	void deleteExam(Long customerId, String examCode);

	void publishExam(Long customerId, String examCode);

	void saveUploadedFiles(List<MultipartFile> asList) throws Exception;


}
