package com.kabalport.sb3app.score.repository;

import com.kabalport.sb3app.score.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {}
