package com.brainworksexams.service;

import java.util.List;

import com.brainworksexams.models.ExamCode;
import com.brainworksexams.models.ExamRespDto;
import com.brainworksexams.models.UserDto;
import com.brainworksexams.models.UserRegDto;

public interface UserService {

	void createUser(UserRegDto userDto);

	UserDto getUserInfo(String name);

	List<ExamRespDto> listExams(String name);

	void registerExam(String name, ExamCode examCode);

}
