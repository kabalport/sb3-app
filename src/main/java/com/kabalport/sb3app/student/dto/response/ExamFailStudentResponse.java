package com.kabalport.sb3app.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ExamFailStudentResponse {
    private final String studentName;
    private final Double avgScore;
}