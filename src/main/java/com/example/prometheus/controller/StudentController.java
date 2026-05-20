package com.example.prometheus.controller;

import com.example.prometheus.model.Student;
import com.example.prometheus.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    //===config repo and config server related data and endpoint with default values in yml file if config server not running=====
    @Value("${student.app-name: Local App}")
    private String appName;

    @Value("${student.max-students: 10}")
    private int maxStudents;

    @Value("${student.subject: Java}")
    private String subject;

    @GetMapping("/config")
    public String config() {
        System.out.println("appName : " + appName);
        System.out.println("maxStudents : " + maxStudents);
        System.out.println("subject : " + subject);
        return appName + " : " + maxStudents + " : " + subject;
    }
}
