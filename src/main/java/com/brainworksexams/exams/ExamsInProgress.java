package com.brainworksexams.exams;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ExamsInProgress {

	private Map<ExamKey, ExamInfo> inprogressExams = new HashMap<>();

	public Optional<ExamInfo> getExamInfo(ExamKey key) {
		return Optional.ofNullable(inprogressExams.get(key));
	}

	public Optional<ExamInfo> getExamInfo(String attemptCode) {
		return Optional.ofNullable(inprogressExams.get(new ExamKey(attemptCode)));
	}
	
	public void addInprogressExam(ExamKey key, ExamInfo val) {
		inprogressExams.put(key, val);
	}
}