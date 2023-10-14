package com.kabalport.sb3app.score.repository;

import com.kabalport.sb3app.score.model.StudentFail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFailRepository extends JpaRepository<StudentFail, Long> {
}
