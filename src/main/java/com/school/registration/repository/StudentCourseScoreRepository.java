package com.school.registration.repository;

import com.school.registration.model.StudentCourseScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseScoreRepository extends JpaRepository<StudentCourseScore, Integer> {

}
