package com.kabalport.sb3app.controller;

import com.kabalport.sb3app.controller.request.SaveExamScoreRequest;
import com.kabalport.sb3app.controller.response.ExamFailStudentResponse;
import com.kabalport.sb3app.controller.response.ExamPassStudentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreApi {

    @PutMapping("/exam/{exam}/score")
    public Object save(@PathVariable("exam") String exam,
            @RequestBody SaveExamScoreRequest request){
            return request;
        }


    @GetMapping("/exam/{exam}/pass")
    public List<ExamPassStudentResponse> pass(@PathVariable("exam") String exam){
        return List.of(new ExamPassStudentResponse("kabalport", 60.0));
    }

    @GetMapping("/exam/{exam}/fail")
    public List<ExamFailStudentResponse> fail(@PathVariable("fail") String exam){
        return List.of(new ExamFailStudentResponse("kabalport", 30.0));
    }




}

