package com.school.registration.repository;

import com.school.registration.model.StudentCourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRegistrationRepository extends JpaRepository<StudentCourseRegistration, Integer> {
}
