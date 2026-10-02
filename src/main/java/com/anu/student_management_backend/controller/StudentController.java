
package com.anu.student_management_backend.controller;

import com.anu.student_management_backend.entity.Student;
import com.anu.student_management_backend.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 2. Get student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // 3. Register a new student
    @PostMapping
    public Student createStudent(
            @Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // 4. Update a student
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    // 5. Delete a student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    // 6. Search by name
    @GetMapping("/search/name")
    public List<Student> searchByName(
            @RequestParam String name) {
        return studentService.searchByName(name);
    }

    // 7. Search by register number
    @GetMapping("/search/register-no")
    public Student searchByRegisterNo(
            @RequestParam String registerNo) {
        return studentService.searchByRegisterNo(registerNo);
    }

    // 8. Filter by department
    @GetMapping("/filter/department")
    public List<Student> filterByDepartment(
            @RequestParam String department) {
        return studentService.filterByDepartment(department);
    }

    // 9. Filter by year
    @GetMapping("/filter/year")
    public List<Student> filterByYear(
            @RequestParam Integer year) {
        return studentService.filterByYear(year);
    }

    // 10. Filter by semester
    @GetMapping("/filter/semester")
    public List<Student> filterBySemester(
            @RequestParam Integer semester) {
        return studentService.filterBySemester(semester);
    }
}
