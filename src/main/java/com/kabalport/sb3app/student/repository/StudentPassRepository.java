package com.kabalport.sb3app.student.repository;

import com.kabalport.sb3app.student.model.StudentPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPassRepository extends JpaRepository<StudentPass, Long> {
}
