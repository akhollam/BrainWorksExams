package com.brainworksexams.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "available_options")
public class Options {

	@Id
	@GeneratedValue
	private Long id;

	private String value;

	private boolean correctAnswer;

	@ManyToOne
	private Question question;

	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private Answer answer;
	
	@CreationTimestamp
	private LocalDateTime createdDatetime;
	
	@UpdateTimestamp
	private LocalDateTime updatedDateTime;

}
