package com.kabalport.sb3app.student.repository;

import com.kabalport.sb3app.student.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {
}
