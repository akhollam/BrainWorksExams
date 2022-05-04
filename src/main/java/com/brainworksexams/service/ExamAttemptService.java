package com.brainworksexams.service;

import com.brainworksexams.exams.ExamQuestion;
import com.brainworksexams.exams.ExamQuestionAndAnswer;
import com.brainworksexams.exams.ExamResult;
import com.brainworksexams.models.ExamAttemptCode;

public interface ExamAttemptService {

	ExamQuestion nextQuestion(String attemptCode);

	ExamQuestion attemptLater(String attemptCode, String questionCode);

	ExamResult finish(String attemptCode);

	ExamAttemptCode startExam(String username, String examCode);

	ExamQuestion answerQuestion(String attemptCode, ExamQuestionAndAnswer qna);

}
