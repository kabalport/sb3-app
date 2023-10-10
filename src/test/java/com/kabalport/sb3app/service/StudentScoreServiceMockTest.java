package com.kabalport.sb3app.service;

import com.kabalport.sb3app.student.repository.StudentFailRepository;
import com.kabalport.sb3app.student.repository.StudentPassRepository;
import com.kabalport.sb3app.student.repository.StudentScoreRepository;
import com.kabalport.sb3app.student.service.StudentScoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StudentScoreServiceMockTest {

    @DisplayName("첫번째 Mock 테스트")
    @Test
    public void firstSaveScoreMockTest(){
        // given
        StudentScoreService studentScoreService = new StudentScoreService(
                Mockito.mock(StudentScoreRepository.class),
                Mockito.mock(StudentPassRepository.class),
                Mockito.mock(StudentFailRepository.class)
        );
        // when
        String givenStudentName = "choidaeyeol";
        String givenExam = "testexam";
        Integer givenKorScore = 80;
        Integer givenEnglishScore = 100;
        Integer givenMathScore = 60;

        studentScoreService.saveScore(givenStudentName,givenExam,givenKorScore,givenEnglishScore,givenMathScore);
    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 이상인경우")
    public void saveScoreMockTest(){
        // given : 평균점수가 60점 이상일 경우
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepository
        );
        String givenStudentName = "kabalport";
        String givenExam = "testexam";
        Integer givenKorScore = 80;
        Integer givenEnglishScore = 100;
        Integer givenMathScore = 60;

        // when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());




    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 미만인경우")
    public void saveScoreMockTest2(){
        // given: 평균점수가 60점 미만인경우
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepository
        );
        String givenStudentName = "kabalport";
        String givenExam = "testexam";
        Integer givenKorScore = 60;
        Integer givenEnglishScore= 10;
        Integer givenMathScore = 20;

        // when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(1)).save(Mockito.any());



    }



}
