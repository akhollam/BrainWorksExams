package com.brainworksexams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainworksexams.entity.Exam;

public interface ExamsRepository extends JpaRepository<Exam, Long> {

	Optional<Exam> findByGlobalExamCode(String examCode);

}
