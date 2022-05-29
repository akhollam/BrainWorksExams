package com.brainworksexams.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private Long id;

	private String text;

	@OneToMany(mappedBy = "question")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Options> options;

	@Cascade(CascadeType.PERSIST)
	@ManyToMany(mappedBy = "questions")
	private List<Exam> exams;
	
	@ManyToMany
	@JoinTable(name = "question_tags", 
			joinColumns = @JoinColumn(name = "question_id"), 
			inverseJoinColumns = @JoinColumn(name = "tag_id"))
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Tag> tags;
	
	@Column(nullable = false, unique = true)
	private String globalQuestionCode;
	
	@CreationTimestamp
	private LocalDateTime createdDatetime;
	
	@UpdateTimestamp
	private LocalDateTime updatedDateTime;

}
