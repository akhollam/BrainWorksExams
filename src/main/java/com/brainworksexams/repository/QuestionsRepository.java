package com.brainworksexams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainworksexams.entity.Question;
import com.brainworksexams.models.QuestionCode;

public interface QuestionsRepository extends JpaRepository<Question, Long> {

	Optional<Question> findByGlobalQuestionCode(QuestionCode questionCode);

}
