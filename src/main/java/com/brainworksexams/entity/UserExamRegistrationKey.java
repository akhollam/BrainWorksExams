package com.brainworksexams.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class UserExamRegistrationKey implements Serializable {

	private static final long serialVersionUID = 1L;

	public UserExamRegistrationKey(Long userId, Long examId) {
		this.userId = userId;
		this.examId = examId;
	}

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "exam_id")
	private Long examId;

}
