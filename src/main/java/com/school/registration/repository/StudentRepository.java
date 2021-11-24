package com.school.registration.repository;

import com.school.registration.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT s FROM Student s INNER JOIN s.registrations as r INNER JOIN r.course as c WHERE c.name = ?1 ORDER BY s.name ASC")
    List<Student> findByCourseNameOrderByNameAsc(String courseName);

    void deleteById(int id);

    //an example if we want to get the student with the registrations included
    @EntityGraph(attributePaths = {"registrations"})
    Optional<Student> findById(int id);

    @Query(value = "SELECT s. FROM Student s WHERE s.id NOT IN (SELECT s2.id FROM Student s2 INNER JOIN s2.registrations r INNER JOIN r.course c WHERE c.name = ?1)")
    List<Student> findStudentsNotRegisteredByCourseName(String courseName);
}
