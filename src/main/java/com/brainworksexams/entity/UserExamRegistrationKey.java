package com.brainworksexams.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class UserExamRegistrationKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	Long userId;

	@Column(name = "exam_id")
	Long examId;

}
