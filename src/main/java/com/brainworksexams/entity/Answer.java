package com.brainworksexams.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Answer {

	@Id
	@GeneratedValue
	private Long id;

	private String text;

	@OneToMany(mappedBy = "question")
	private List<Options> options;

	@CreationTimestamp
	private LocalDateTime createdDatetime;

	@UpdateTimestamp
	private LocalDateTime updatedDateTime;

}
