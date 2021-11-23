package com.school.registration.repository;

import com.school.registration.model.StudentCourseScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseScoreRepository extends JpaRepository<StudentCourseScore, Integer> {
    List<StudentCourseScore> findByRegistrationId(int id);
}
