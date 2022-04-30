package com.brainworksexams.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDto {

	@JsonProperty("full_name")
	private String fullName;
	
	private String gender;
	
	private String email;
	
	@JsonProperty("contact_number")
	private String contactNumber;
	
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	private String username;
	
	@JsonIgnore
	private String password;
}
