package com.brainworksexams.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.brainworksexams.entity.Answer;
import com.brainworksexams.entity.Exam;
import com.brainworksexams.entity.Options;
import com.brainworksexams.entity.Question;
import com.brainworksexams.exceptions.InvalidInputException;
import com.brainworksexams.models.OptionsDto;
import com.brainworksexams.models.QuestionCode;
import com.brainworksexams.models.QuestionDto;
import com.brainworksexams.repository.ExamsRepository;
import com.brainworksexams.repository.OptionsRepository;
import com.brainworksexams.repository.QuestionsRepository;
import com.brainworksexams.util.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuestionsServiceImpl implements QuestionsService {

	@Autowired
	private QuestionsRepository questionsRepository;

	@Autowired
	private ExamsRepository examsRepository;

	@Autowired
	private OptionsRepository optionsRepository;

	@Override
	public void mapExamQuestion(Long customerId, String examCode, QuestionCode questionCode) {

		Optional<Exam> exm = examsRepository.findByGlobalExamCode(examCode);
		exm.ifPresent(e -> {
			log.debug("Exam found adding question now. - {}", questionCode);
			Optional<Question> que = questionsRepository.findByGlobalQuestionCode(questionCode.getQuestionCode());
			if (que.isPresent() && exm.isPresent()) {
				Exam exam = exm.get();
				exm.get().getQuestions().add(que.get());
				examsRepository.save(exam);
			} else {
				throw new InvalidInputException("Invalid question code.");
			}
		});
	}

	@Override
	public void addExamQuestion(Long customerId, String examCode, QuestionDto questionDto) {

		Optional<Exam> exm = examsRepository.findByGlobalExamCode(examCode);
		exm.ifPresent(e -> {

			Question question = new Question();
			question.setGlobalQuestionCode(Utility.uuid());
			question.setText(questionDto.getText());

			questionsRepository.save(question);

			List<Options> options = new ArrayList<Options>();
			ArrayList<OptionsDto> allOptions = questionDto.getOptions();
			for (OptionsDto optionsDto : allOptions) {
				Options op = new Options();
				Answer ans = new Answer();
				ans.setText(optionsDto.getAnswerText());
				op.setCorrectAnswer(optionsDto.isCorrectAnswer());
				op.setAnswer(ans);
				op.setQuestion(question);
				options.add(op);
			}

			optionsRepository.saveAll(options);
			e.getQuestions().add(question);
			examsRepository.save(e);
		});

	}

	@Override
	public void deleteExamQuestion(Long customerId, String examCode, String questionCode) {

		Optional<Exam> exm = examsRepository.findByGlobalExamCode(examCode);
		exm.ifPresent(e -> {
			ListIterator<Question> listItr = e.getQuestions().listIterator();
			while (listItr.hasNext()) {
				Question question = (Question) listItr.next();
				if (question.getGlobalQuestionCode().equalsIgnoreCase(questionCode)) {
					listItr.remove();
				}
			}
			examsRepository.saveAndFlush(e);
		});
	}

	@Override
	@Transactional
	public void deleteExam(Long customerId, String examCode) {
		examsRepository.findByGlobalExamCode(examCode).ifPresent(ex -> {
			ex.setDeleted(true);
			examsRepository.save(ex);
		});
	}

	@Override
	public void publishExam(Long customerId, String examCode) {
		examsRepository.findByGlobalExamCode(examCode).ifPresent(ex -> {
			ex.setPublish(true);
			examsRepository.save(ex);
		});
	}

	@Override
	public void saveUploadedFiles(List<MultipartFile> files) throws Exception {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue;
			}

			InputStream stream = new ByteArrayInputStream(file.getBytes());
			Workbook workbook = new XSSFWorkbook(stream);
			Sheet sheet = workbook.getSheetAt(0);

			Map<Integer, List<String>> data = new HashMap<>();
			int i = 0;
			for (Row row : sheet) {
				data.put(i, new ArrayList<String>());
				for (Cell cell : row) {
					data.get(i).add(cell.getStringCellValue());
				}
				i++;
			}
			workbook.close();
			log.debug("-------------------------------");
			log.debug("{}", data);
			log.debug("-------------------------------");

		}

	}

}
