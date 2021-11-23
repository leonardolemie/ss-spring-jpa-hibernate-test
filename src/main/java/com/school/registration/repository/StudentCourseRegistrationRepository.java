package com.school.registration.repository;

import com.school.registration.model.StudentCourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentCourseRegistrationRepository extends JpaRepository<StudentCourseRegistration, Integer> {

    Optional<StudentCourseRegistration> findByStudentId(int studentId);
}
