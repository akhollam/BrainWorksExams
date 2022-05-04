package com.brainworksexams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainworksexams.entity.UserExamRegistration;
import com.brainworksexams.entity.UserExamRegistrationKey;

public interface UserExamRegistrationRepository extends JpaRepository<UserExamRegistration, UserExamRegistrationKey> {


}
