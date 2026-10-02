
package com.anu.student_management_backend.service;

import com.anu.student_management_backend.entity.Student;
import com.anu.student_management_backend.repository.StudentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 1. Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. Get student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found with ID: " + id));
    }

    // 3. Register a new student
    public Student createStudent(Student student) {

        if (studentRepository.findByRegisterNo(
                student.getRegisterNo()).isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Register number already exists");
        }

        // Timestamps are managed by @PrePersist in Student.java
        return studentRepository.save(student);
    }

    // 4. Update an existing student
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = getStudentById(id);

        if (!existingStudent.getRegisterNo().equals(
                updatedStudent.getRegisterNo())
                && studentRepository.findByRegisterNo(
                updatedStudent.getRegisterNo()).isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Register number already exists");
        }

        existingStudent.setRegisterNo(updatedStudent.getRegisterNo());
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPhone(updatedStudent.getPhone());
        existingStudent.setDepartment(updatedStudent.getDepartment());
        existingStudent.setYear(updatedStudent.getYear());
        existingStudent.setSemester(updatedStudent.getSemester());

        // @PreUpdate automatically updates updatedAt
        return studentRepository.save(existingStudent);
    }

    // 5. Delete a student
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    // 6. Search students by name
    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    // 7. Search students by register number
    public Student searchByRegisterNo(String registerNo) {
        return studentRepository.findByRegisterNo(registerNo)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found with register number: "
                                        + registerNo));
    }

    // 8. Filter students by department
    public List<Student> filterByDepartment(String department) {
        return studentRepository.findByDepartmentIgnoreCase(department);
    }

    // 9. Filter students by year
    public List<Student> filterByYear(Integer year) {
        return studentRepository.findByYear(year);
    }

    // 10. Filter students by semester
    public List<Student> filterBySemester(Integer semester) {
        return studentRepository.findBySemester(semester);
    }
}
