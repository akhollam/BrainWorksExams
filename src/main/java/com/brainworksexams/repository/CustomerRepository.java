package com.brainworksexams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainworksexams.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
