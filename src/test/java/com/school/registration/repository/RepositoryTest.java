package com.school.registration.repository;

import com.school.registration.model.Course;
import com.school.registration.model.Student;
import com.school.registration.model.StudentCourseRegistration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RepositoryTest {
    /*
        These tests would ideally in separate classes but for the purpose of this exercise I kept it all here.
     */

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentCourseScoreRepository studentCourseScoreRepository;

    @Autowired
    StudentCourseRegistrationRepository studentCourseRegistrationRepository;

    @Test
    public void findById_shouldFindUser_whenIdProvided() {
        var student = studentRepository.findById(1).orElseThrow();
        assertThat(student.getId()).isEqualTo(1);
        assertThat(student.getName()).isEqualTo("Leonardo");
    }

    @Test
    public void save_shouldSaveStudent_whenRegistrationIsPresent() {
        var course = courseRepository.findById(1).orElseThrow();
        var student = Student.builder().name("Joao").build();
        var registration = StudentCourseRegistration.builder().student(student).course(course).build();
        student.setRegistrations(List.of(registration));
        studentRepository.save(student);

        assertThat(studentRepository.findById(student.getId())).isEqualTo(Optional.of(student));
    }

    @Test
    public void deleteById_shouldDeleteAndCascade() {
        var studentWithRegistration = studentRepository.findById(1).orElseThrow();
        var registration = studentWithRegistration.getRegistrations();
        assertThat(registration).isNotEmpty();
        assertThat(registration.get(0).getScores()).isNotEmpty();
        assertThat(studentCourseScoreRepository.findByRegistrationId(registration.get(0).getId())).isNotEmpty();

        studentRepository.deleteById(studentWithRegistration.getId());
        assertThat(studentRepository.findById(studentWithRegistration.getId())).isNotPresent();
        assertThat(studentCourseRegistrationRepository.findByStudentId(studentWithRegistration.getId())).isNotPresent();
    }

    @Test
    public void findStudentsNotRegisteredByCourseName() {
        assertThat(studentRepository.findStudentsNotRegisteredByCourseName("Programming")).hasSize(3);
    }

    @Test
    public void save_shouldSaveCourse() {
        var course = Course.builder().name("Math 2").build();
        courseRepository.save(course);
        assertThat(courseRepository.findById(course.getId())).isEqualTo(Optional.of(course));
    }

    @Test
    public void findByCourseNameOrderByNameAsc_shouldReturnStudentsOrdered() {
        var studentsRegistered = studentRepository.findByCourseNameOrderByNameAsc("Programming");
        assertThat(studentsRegistered.stream().map(Student::getName)).containsExactly("Arun", "Leonardo", "Rick");
    }
}
