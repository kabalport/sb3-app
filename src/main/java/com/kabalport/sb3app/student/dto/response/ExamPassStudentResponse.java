package com.kabalport.sb3app.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExamPassStudentResponse {
    private final String studentName;
    private final Double avgScore;
}
