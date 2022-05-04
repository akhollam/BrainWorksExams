package com.brainworksexams.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brainworksexams.entity.Exam;
import com.brainworksexams.entity.User;
import com.brainworksexams.entity.UserExamRegistration;
import com.brainworksexams.exceptions.NotFoundException;
import com.brainworksexams.models.ExamCode;
import com.brainworksexams.models.ExamRespDto;
import com.brainworksexams.models.UserDto;
import com.brainworksexams.models.UserRegDto;
import com.brainworksexams.repository.ExamsRepository;
import com.brainworksexams.repository.UserExamRegistrationRepository;
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

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ExamsRepository examsRepository;
	
	@Autowired
	private UserExamRegistrationRepository userExamRegistrationRepository; 

	@Override
	public void createUser(UserRegDto userDto) {
		log.debug("userDto - {} ", userDto);
		User user = mapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setUserCode(Utility.uuid());
		userRepository.save(user);
	}

	@Override
	public UserDto getUserInfo(String name) {
		Optional<User> userOp = userRepository.findByUsername(name);
		if (userOp.isPresent()) {
			return mapper.map(userOp.get(), UserDto.class);
		}
		throw new NotFoundException("User not found.");
	}

	@Override
	public List<ExamRespDto> listExams(String name) {
		Optional<User> userOp = userRepository.findByUsername(name);
		if (userOp.isPresent()) {
			return userOp.get().getUserExams().stream().map(exm -> {
				return mapper.map(exm, ExamRespDto.class);
			}).collect(Collectors.toList());
		}
		throw new NotFoundException("User not found.");
	}

	@Override
	public void registerExam(String name, ExamCode examCode) {
		Optional<User> userOp = userRepository.findByUsername(name);
		Optional<Exam> examOp = examsRepository.findByGlobalExamCode(examCode.getExamCode());
		if (userOp.isPresent() && examOp.isPresent()) {
			UserExamRegistration userExamRegistration = new UserExamRegistration();
			userExamRegistration.setExam(examOp.get());
			userExamRegistration.setUser(userOp.get());
			userExamRegistrationRepository.save(userExamRegistration);
		}
	}

}
