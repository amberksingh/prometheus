package com.example.prometheus.controller;

import com.example.prometheus.model.Student;
import com.example.prometheus.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping("/bulk")
    public List<Student> addStudents(@RequestBody List<Student> students) {
        return studentService.addStudents(students);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/old-school/{id}")
    public Student getStudentOldSchool(@PathVariable Long id) {
        return studentService.getStudentByIdOldSchool(id);
    }
}
