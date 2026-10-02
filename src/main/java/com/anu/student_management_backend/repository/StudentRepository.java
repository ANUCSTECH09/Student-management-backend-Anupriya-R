
package com.anu.student_management_backend.repository;

import com.anu.student_management_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByRegisterNo(String registerNo);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByDepartmentIgnoreCase(String department);

    List<Student> findByYear(Integer year);

    List<Student> findBySemester(Integer semester);
}
