package com.brainworksexams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brainworksexams.models.TagDto;
import com.brainworksexams.service.TagService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tags")
@Tag(name = "Tags API", description = "Tags details. ")
public class TagsController {
	
	@Autowired
	private TagService tagService; 
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createTag() {
		log.debug("creating tag");
	}
	
	@GetMapping
	public List<TagDto> listTags() {
		return tagService.listTags();
	}

}
