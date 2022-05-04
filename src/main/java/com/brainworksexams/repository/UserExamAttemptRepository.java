package com.brainworksexams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainworksexams.entity.UserExamAttempt;

public interface UserExamAttemptRepository extends JpaRepository<UserExamAttempt, Long> {

}
