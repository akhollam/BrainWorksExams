package com.brainworksexams.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private String userCode;

	@Column(nullable = false)
	private String fullName;

	private String gender;
	
	@NotNull
	@Size(max = 250)
	@Pattern(regexp = "^([^ @])+@([^ \\.@]+\\.)+([^ \\.@])+$")
	@Column(name = "email", nullable = true, length = 250, unique = true)
	private String email;

	private String contactNumber;

	private LocalDate birthDate;

	@Column(nullable = false, unique = true)
	private String username;

	private String password;

	@OneToMany(mappedBy = "user")
	private List<UserExamRegistration> userExams;

	@CreationTimestamp
	private LocalDateTime createdDatetime;

	@UpdateTimestamp
	private LocalDateTime updatedDateTime;

}