package com.kabalport.sb3app.service;

import com.kabalport.sb3app.student.dto.response.ExamFailStudentResponse;
import com.kabalport.sb3app.student.dto.response.ExamPassStudentResponse;
import com.kabalport.sb3app.student.model.StudentFail;
import com.kabalport.sb3app.student.model.StudentPass;
import com.kabalport.sb3app.student.model.StudentScore;
import com.kabalport.sb3app.student.repository.StudentFailRepository;
import com.kabalport.sb3app.student.repository.StudentPassRepository;
import com.kabalport.sb3app.student.repository.StudentScoreRepository;
import com.kabalport.sb3app.student.service.StudentScoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

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

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass.class);


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

    @Test
    @DisplayName("합격자 명단 가져오기 검증")
    public void getPassStudentsListTest(){
        // given
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentPass expectStudent1 = StudentPass.builder().id(1L).studentName("kabalport").exam("testexam").avgScore(70.0).build();
        StudentPass expectStudent2 = StudentPass.builder().id(2L).studentName("test").exam("testexam").avgScore(80.0).build();
        StudentPass notExpectStudent3 = StudentPass.builder().id(3L).studentName("test").exam("aaa").avgScore(90.0).build();

        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
               expectStudent1, expectStudent2, notExpectStudent3
        ));



        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepository
        );
        String givenTestExam = "testexam";

        // when
        var expectResponse = List.of(expectStudent1, expectStudent2).stream()
                .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore())).toList();
        List<ExamPassStudentResponse> responses = studentScoreService.getPassStudentList(givenTestExam);


//        responses.forEach((response) -> System.out.println(response.getStudentName() + " " + response.getAvgScore()));
        // then
        Assertions.assertIterableEquals(expectResponse, responses);


        expectResponse.forEach((response)->{
            System.out.println(response.getStudentName()+" "+response.getAvgScore());

        });
        System.out.println("====");

        responses.forEach((response)->{
            System.out.println(response.getStudentName()+" "+response.getAvgScore());
        });

    }

    @Test
    @DisplayName("불합격자 명단 가져오기 검증")
    public void getFailStudentsListTest(){
        // given
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        String givenTestExam = "testexam";
        StudentFail NotExpectStudent3 = StudentFail.builder().id(1L).studentName("kabalport").exam("aaa").avgScore(20.0).build();
        StudentFail expectStudent1 = StudentFail.builder().id(2L).studentName("test").exam(givenTestExam).avgScore(30.0).build();
        StudentFail expectStudent2 = StudentFail.builder().id(3L).studentName("test").exam(givenTestExam).avgScore(10.0).build();

        Mockito.when(studentFailRepository.findAll()).thenReturn(List.of(
                expectStudent1, expectStudent2, NotExpectStudent3
        ));

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepository
        );
        // when
        var expectFailList = List.of(expectStudent1, expectStudent2)
                .stream().map(
                        (fail) ->new ExamFailStudentResponse(fail.getStudentName(), fail.getAvgScore())
                ).toList();
        List<ExamFailStudentResponse> responses = studentScoreService.getFailStudentList(givenTestExam);

        // then
        Assertions.assertIterableEquals(expectFailList, responses);

    }


}
