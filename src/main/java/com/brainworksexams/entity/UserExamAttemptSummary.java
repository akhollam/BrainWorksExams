package com.brainworksexams.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class UserExamAttemptSummary {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private UserExamAttempt userExamAttempt;
	
	@OneToOne
	private Question question;
	
	@OneToOne
	private Answer selection;
}
