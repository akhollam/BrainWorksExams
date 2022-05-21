package com.brainworksexams.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String description;
	
	private String imageUrl;

	@Cascade(CascadeType.PERSIST)
	@OneToMany(mappedBy = "customer")
	private List<Exam> exams;
	
	@CreationTimestamp
	private LocalDateTime createdDatetime;
	
	@UpdateTimestamp
	private LocalDateTime updatedDateTime;

}
