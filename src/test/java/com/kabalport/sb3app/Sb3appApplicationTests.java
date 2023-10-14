package com.kabalport.sb3app;

import com.kabalport.sb3app.score.model.StudentScoreFixture;
import com.kabalport.sb3app.score.repository.StudentScoreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class Sb3appApplicationTests extends IntegrationTest {
	@Autowired
	private StudentScoreRepository studentScoreRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	void contextLoads() {
		var studentScore = StudentScoreFixture.passed();
		var savedStudentScore = studentScoreRepository.save(studentScore);

		entityManager.flush();
		entityManager.clear();

		var queryStudentScore = studentScoreRepository.findById(savedStudentScore.getId()).orElseThrow();

		Assertions.assertEquals(savedStudentScore.getId(), queryStudentScore.getId());
		Assertions.assertEquals(savedStudentScore.getStudentName(), queryStudentScore.getStudentName());
		Assertions.assertEquals(savedStudentScore.getMathScore(), queryStudentScore.getMathScore());
		Assertions.assertEquals(savedStudentScore.getEnglishScore(), queryStudentScore.getEnglishScore());
		Assertions.assertEquals(savedStudentScore.getKorScore(), queryStudentScore.getKorScore());
	}

}
