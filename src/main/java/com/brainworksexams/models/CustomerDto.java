package com.brainworksexams.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerDto {

	private Long id;

	private String name;

	private String description;
	
	private String port;

	@JsonProperty("image_url")
	private String imageUrl;

}
