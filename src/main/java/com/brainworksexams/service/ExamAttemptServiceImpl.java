package com.brainworksexams.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainworksexams.entity.Exam;
import com.brainworksexams.entity.User;
import com.brainworksexams.entity.UserExamAttempt;
import com.brainworksexams.entity.UserExamRegistration;
import com.brainworksexams.entity.UserExamRegistrationKey;
import com.brainworksexams.exams.ExamInfo;
import com.brainworksexams.exams.ExamKey;
import com.brainworksexams.exams.ExamQuestion;
import com.brainworksexams.exams.ExamQuestionAndAnswer;
import com.brainworksexams.exams.ExamResult;
import com.brainworksexams.exams.ExamsInProgress;
import com.brainworksexams.exceptions.NotFoundException;
import com.brainworksexams.models.ExamAttemptCode;
import com.brainworksexams.repository.ExamsRepository;
import com.brainworksexams.repository.UserExamAttemptRepository;
import com.brainworksexams.repository.UserExamRegistrationRepository;
import com.brainworksexams.repository.UserRepository;
import com.brainworksexams.util.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExamAttemptServiceImpl implements ExamAttemptService {

	@Autowired
	private ExamsInProgress examsInProgress;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExamsRepository examsRepository;

	@Autowired
	private UserExamRegistrationRepository userExamRegistrationRepository;

	@Autowired
	private UserExamAttemptRepository examAttemptRepository;

	@Override
	public ExamQuestion nextQuestion(String attemptCode) {
		Optional<ExamInfo> examInfo = examsInProgress.getExamInfo(attemptCode);
		if (examInfo.isPresent()) {
			return examInfo.get().getNextQuestion();
		}
		throw new NotFoundException("Next question not found. ");
	}

	@Override
	public ExamQuestion attemptLater(String attemptCode, String questionCode) {
		Optional<ExamInfo> examInfo = examsInProgress.getExamInfo(attemptCode);
		if (examInfo.isPresent()) {
			return examInfo.get().attempedLaterQuestion(questionCode);
		}
		throw new NotFoundException("Next question not found. ");
	}

	@Override
	public ExamResult finish(String attemptCode) {
		return null;
	}

	@Override
	public ExamAttemptCode startExam(String username, String examCode) {

		Optional<Exam> examOp = examsRepository.findByGlobalExamCode(examCode);
		Optional<User> userOp = userRepository.findByUsername(username);

		if (examOp.isPresent() && userOp.isPresent()) {

			Exam exam = examOp.get();
			User user = userOp.get();

			Optional<UserExamRegistration> examRegOp = userExamRegistrationRepository
					.findById(new UserExamRegistrationKey(user.getId(), exam.getId()));

			if (examRegOp.isPresent()) {

				UserExamRegistration examReg = examRegOp.get();

				String attemptCode = Utility.uuid();
				UserExamAttempt attempt = new UserExamAttempt();
				attempt.setExamAttemptCode(attemptCode);
				attempt.setUserExamRegistration(examReg);
				examAttemptRepository.save(attempt);

				ExamKey key = new ExamKey(attemptCode);
				examsInProgress.addInprogressExam(key, toExaminfo(exam));

				return new ExamAttemptCode(attemptCode);
			}

		}
		throw new NotFoundException("Something went wrong. Please try later.");
	}

	private ExamInfo toExaminfo(Exam exam) {

		ExamInfo info = new ExamInfo();
		info.setDurationInMinutes(exam.getDurationInMinutes());
		return info;

	}

	@Override
	public ExamQuestion answerQuestion(String attemptCode, ExamQuestionAndAnswer qna) {
		Optional<ExamInfo> examInfo = examsInProgress.getExamInfo(attemptCode);
		if (examInfo.isPresent()) {
			return examInfo.get().setQuestionAttemped(qna.getQuestionCode(), qna.getAnswerId());
		}
		throw new NotFoundException("Next question not found. ");
	}

}
