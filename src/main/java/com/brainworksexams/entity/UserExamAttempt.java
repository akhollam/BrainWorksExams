package com.brainworksexams.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class UserExamAttempt {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private UserExamRegistration userExamRegistration;
	
	private LocalDateTime attemptDateTime;
	
	@Cascade(CascadeType.DELETE)
	@OneToMany(mappedBy = "userExamAttempt")
	private List<UserExamAttemptSummary> userExamAttemptSummary;
	
	@Column(nullable = false, unique = true)
	private String examAttemptCode;
	
	@CreationTimestamp
	private LocalDateTime createdDatetime;
	
	@UpdateTimestamp
	private LocalDateTime updatedDateTime;
	
}
