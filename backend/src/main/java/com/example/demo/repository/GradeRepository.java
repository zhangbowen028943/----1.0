package com.example.demo.repository;

import com.example.demo.model.Grade;

public interface GradeRepository {
    Grade findByStudentAndCourse(Long studentId, Long courseId);
    void save(Grade grade);
    void update(Grade grade);
}