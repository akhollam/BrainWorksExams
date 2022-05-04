package com.brainworksexams.exams;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ExamInfo {

	public ExamInfo() {
		this.startTime = LocalDateTime.now();
	}

	private LocalDateTime startTime;

	private short durationInMinutes;

	private List<ExamQuestion> questions;

	public ExamQuestion getNextQuestion() {

		Optional<ExamQuestion> opNxtQue = this.questions.stream().filter(que -> !que.isAttempted())
				.filter(que -> !que.isCheckLater()).findAny();

		if (opNxtQue.isPresent()) {
			return opNxtQue.get();
		}

		return null;
	}

	public ExamQuestion setQuestionAttemped(String queCode, Long answer) {
		Optional<ExamQuestion> opQue = this.questions.stream().filter(que -> que.getQuestionCode().equals(queCode))
				.findFirst();
		opQue.ifPresent(exmQ -> {
			exmQ.setAttempted(true);
			exmQ.setSelectedAnswer(answer);
		});
		return getNextQuestion();
	}

	public ExamQuestion attempedLaterQuestion(String queCode) {
		Optional<ExamQuestion> opQue = this.questions.stream().filter(que -> que.getQuestionCode().equals(queCode))
				.findFirst();
		opQue.ifPresent(exmQ -> {
			exmQ.setCheckLater(true);
		});
		return getNextQuestion();
	}

}
