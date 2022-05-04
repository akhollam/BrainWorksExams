package com.brainworksexams.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegDto extends UserDto {

	@NotBlank
	@Size(min = 8, message = "Password should be atleast 8 char long. ")
	protected String password;

}
