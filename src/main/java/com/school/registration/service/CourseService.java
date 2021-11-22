package com.school.registration.service;

import com.school.registration.model.Course;
import com.school.registration.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;

    public List<Course> getCourses(List<Integer> ids){
        return courseRepository.findAllById(ids);
    }
}
