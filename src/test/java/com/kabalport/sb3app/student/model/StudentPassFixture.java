package com.kabalport.sb3app.student.model;

import com.kabalport.sb3app.calcaulator.MyCalculator;
import com.kabalport.sb3app.student.model.StudentPass;
import com.kabalport.sb3app.student.model.StudentScore;

public class StudentPassFixture {
    public static StudentPass create(StudentScore studentScore){
        var calculator = new MyCalculator();
        return StudentPass
                .builder()
                .exam(studentScore.getExam())
                .studentName(studentScore.getStudentName())
                .avgScore(calculator
                        .add(studentScore.getKorScore().doubleValue())
                        .add(studentScore.getEnglishScore().doubleValue())
                        .add(studentScore.getMathScore().doubleValue())
                        .divide(3.0)
                        .getResult()
                )
                .build();
    }

    public static StudentPass create(String studentName, String exam){
        return StudentPass
                .builder()
                .studentName(studentName)
                .exam(exam)
                .avgScore(80.0)
                .build();
    }

}
