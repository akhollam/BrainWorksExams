package com.brainworksexams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainworksexams.entity.Answer;

public interface AnswersRepository extends JpaRepository<Answer, Long> {

}
