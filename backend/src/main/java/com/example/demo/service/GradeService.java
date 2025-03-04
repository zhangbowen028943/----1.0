package com.example.demo.service;

import com.example.demo.mapper.GradeMapper;
import com.example.demo.model.Grade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;

    @Transactional
    public void recordGrade(Grade grade) {
        Grade existing = gradeRepository.findByStudentAndCourse(grade.getStudentId(), grade.getCourseId());
        if (existing != null) {
            grade.setId(existing.getId());
            gradeRepository.update(grade);
        } else {
            gradeRepository.save(grade);
        }
    }

    public Grade getStudentGrade(Long studentId, Long courseId) {
        return gradeRepository.findByStudentAndCourse(studentId, courseId);
    }

    @Transactional
    public void updateGradeRemark(Long gradeId, String remark) {
        Grade grade = gradeMapper.findByStudentAndCourse(null, null); // 实际应根据ID查询
        grade.setRemark(remark);
        gradeRepository.update(grade);
    }
}