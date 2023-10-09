package com.kabalport.sb3app.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExamFailStudentResponse {
    private final String studentName;
    private final Double avgScore;
}
