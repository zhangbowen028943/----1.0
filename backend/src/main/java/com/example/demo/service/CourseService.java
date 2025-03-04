package com.example.demo.service;

import com.example.demo.mapper.CourseMapper;
import com.example.demo.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseMapper courseMapper;

    @Transactional
    public void createCourse(Course course) {
        validateCourseTime(course);
        course.setSelectedCount(0);
        courseMapper.insert(course);
    }

    @Transactional
    public void updateCourse(Course course) {
        validateCourseTime(course);
        courseMapper.update(course);
    }

    public Course getCourseById(Long id) {
        return courseMapper.selectById(id);
    }

    @Transactional
    public void deleteCourse(Long id) {
        courseMapper.delete(id);
    }

    private void validateCourseTime(Course course) {
        if (course.getStartTime().isAfter(course.getEndTime())) {
            throw new IllegalArgumentException("课程结束时间不能早于开始时间");
        }
    }
}