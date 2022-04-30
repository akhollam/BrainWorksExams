package com.brainworksexams.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainworksexams.entity.Exam;
import com.brainworksexams.entity.Question;
import com.brainworksexams.models.QuestionCode;
import com.brainworksexams.repository.ExamsRepository;
import com.brainworksexams.repository.OptionsRepository;
import com.brainworksexams.repository.QuestionsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuestionsServiceImpl implements QuestionsService {

	@Autowired
	private QuestionsRepository questionsRepository;

	@Autowired
	private ExamsRepository examsRepository;

	@Autowired
	private OptionsRepository optionsRepository;

	@Override
	public void mapExamQuestion(Long customerId, String examCode, QuestionCode questionCode) {

		Optional<Exam> exams = examsRepository.findByGlobalExamCode(examCode);
		exams.ifPresent(e -> {

			Optional<Question> que = questionsRepository.findByGlobalQuestionCode(questionCode);
			Optional<Exam> exm = examsRepository.findByGlobalExamCode(examCode);
			if (que.isPresent() && exm.isPresent()) {
				Exam exam = exm.get();
				exm.get().getQuestions().add(que.get());
				examsRepository.save(exam);
			}
			
		});

	}

}
