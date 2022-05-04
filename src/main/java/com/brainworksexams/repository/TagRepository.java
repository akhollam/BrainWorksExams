package com.brainworksexams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainworksexams.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
