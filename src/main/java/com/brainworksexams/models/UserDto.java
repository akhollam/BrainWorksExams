package com.brainworksexams.models;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
@Getter(value = AccessLevel.PUBLIC)
public class UserDto {

	@NotBlank
	@JsonProperty("full_name")
	protected String fullName;
	
	protected String gender;
	
	@NotNull
	@Pattern(regexp = "^([^ @])+@([^ \\.@]+\\.)+([^ \\.@])+$")
	protected String email;
	
	@JsonProperty("contact_number")
	protected String contactNumber;
	
	@JsonProperty("birth_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	protected LocalDate birthDate;
	
	@NotBlank
	@Size(min = 6, message = "Username should be atleast 6 char long. ")
	protected String username;
	
}
