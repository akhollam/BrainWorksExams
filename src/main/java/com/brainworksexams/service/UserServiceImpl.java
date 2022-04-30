package com.brainworksexams.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainworksexams.entity.User;
import com.brainworksexams.models.UserDto;
import com.brainworksexams.repository.UserRepository;
import com.brainworksexams.util.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void createUser(UserDto userDto) {
		log.debug("userDto - {} ", userDto);
		User user = mapper.map(userDto, User.class);
		user.setUserCode(Utility.uuid());
		userRepository.save(user);
	}

}
