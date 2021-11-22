package com.school.registration.service;

import com.school.registration.dto.in.StudentRequest;
import com.school.registration.model.Course;
import com.school.registration.model.Student;
import com.school.registration.model.StudentCourseRegistration;
import com.school.registration.repository.CourseRepository;
import com.school.registration.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Transactional
    public Student createStudent(StudentRequest studentRequest) {
        List<Course> courses = courseRepository.findAllById(studentRequest.getCourseIds());
        Student student = Student.builder().name(studentRequest.getName()).build();

        List<StudentCourseRegistration> studentCourseRegistrations = courses.stream()
                .map(c -> StudentCourseRegistration.builder().student(student).course(c).build())
                .collect(Collectors.toList());

        student.setRegistrations(studentCourseRegistrations);
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        //ideally there would be no "deleting" a student but just setting it to inactive
        studentRepository.deleteById(id);
    }

    public Student getStudent(int id) {
        return studentRepository.findById(id);
    }
}
