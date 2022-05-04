package com.brainworksexams.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainworksexams.models.TagDto;
import com.brainworksexams.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public List<TagDto> listTags() {
		return tagRepository.findAll().stream().map(tag -> {
			return new TagDto(tag.getName());
		}).collect(Collectors.toList());
	}

}
