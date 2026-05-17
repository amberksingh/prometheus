package com.example.prometheus.service;

import com.example.prometheus.model.Student;
import com.example.prometheus.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DataSource dataSource;

    private void printConnection() {

        try (Connection connection = dataSource.getConnection()) {

            System.out.println("Connection used = " + connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getStudent(Long id) {
        printConnection();
        System.out.println("get studentById");
//        try (Connection connection = dataSource.getConnection()) {
//            System.out.println("Connection used = " + connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student addStudent(Student student) {
        printConnection();
        System.out.println("Adding single student");

        return studentRepository.save(student);
    }

    public List<Student> addStudents(List<Student> students) {
        printConnection();
        System.out.println("Adding multiple students");

        return studentRepository.saveAll(students);
    }

    public Student getStudentByIdOldSchool(Long id) {

        System.out.println("getStudentByIdOldSchool");
        String sql = "select id, name, age from student where id = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            System.out.println("Old school connection = " + connection);

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Student student = new Student();

                    student.setId(rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setAge(rs.getInt("age"));

                    return student;
                }
            }

            throw new RuntimeException("Student not found");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}