package com.example.prometheus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;

    public Student() {
    }

    public Student(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // getters setters
}
