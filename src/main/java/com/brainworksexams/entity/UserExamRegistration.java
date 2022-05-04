package com.brainworksexams.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class UserExamRegistration {

	@EmbeddedId
	private UserExamRegistrationKey userExamKey;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("examId")
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@Cascade(CascadeType.PERSIST)
	@OneToMany(mappedBy = "userExamRegistration")
	private List<UserExamAttempt> userExamAttempts;

	@CreationTimestamp
	private LocalDateTime createdDatetime;

	@UpdateTimestamp
	private LocalDateTime updatedDateTime;

}
