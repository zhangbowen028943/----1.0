package com.example.demo.repository.impl;

import com.example.demo.mapper.GradeMapper;
import com.example.demo.model.Grade;
import com.example.demo.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GradeRepositoryImpl implements GradeRepository {
    private final GradeMapper gradeMapper;

    @Override
    public Grade findByStudentAndCourse(Long studentId, Long courseId) {
        return gradeMapper.findByStudentAndCourse(studentId, courseId);
    }

    @Override
    public void save(Grade grade) {
        gradeMapper.insert(grade);
    }

    @Override
    public void update(Grade grade) {
        gradeMapper.update(grade);
    }
}