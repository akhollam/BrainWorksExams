package com.brainworksexams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainworksexams.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
