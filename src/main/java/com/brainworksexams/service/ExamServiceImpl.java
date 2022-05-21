package com.brainworksexams.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainworksexams.entity.Exam;
import com.brainworksexams.entity.Question;
import com.brainworksexams.exceptions.NotFoundException;
import com.brainworksexams.models.ExamRespDto;
import com.brainworksexams.models.QuestionDto;
import com.brainworksexams.repository.ExamsRepository;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamsRepository examsRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<QuestionDto> listQuestions(Long customerId, String examCode) {

		Optional<Exam> exam = examsRepository.findDetailsByGlobalExamCode(examCode);
		if (exam.isPresent()) {
			Exam e = exam.get();
			List<Question> que = e.getQuestions();
			return que.stream().map(q -> {
				return mapper.map(q, QuestionDto.class);
			}).collect(Collectors.toList());
		}

		throw new NotFoundException("Exam not found.");
	}

	@Override
	public ExamRespDto getExam(Long customerId, String examCode) {
		Optional<Exam> exam = examsRepository.findByGlobalExamCode(examCode);
		if (exam.isPresent()) {
			Exam e = exam.get();
			return mapper.map(e, ExamRespDto.class);
		}
		throw new NotFoundException("Exam not found.");
	}

}
