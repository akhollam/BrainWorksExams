package com.brainworksexams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brainworksexams.entity.Exam;

public interface ExamsRepository extends JpaRepository<Exam, Long> {

	Optional<Exam> findByGlobalExamCode(String examCode);

	@Query(value = "SELECT ex FROM Exam ex "
					+ "LEFT JOIN FETCH ex.questions que "
					+ "WHERE ex.globalExamCode = :examCode")
	Optional<Exam> findDetailsByGlobalExamCode(String examCode);

}
