package com.example.demo.service;

import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.CourseSelectionMapper;
import com.example.demo.model.Course;
import com.example.demo.model.CourseSelection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseSelectionService {
    private final CourseSelectionMapper selectionMapper;
    private final CourseMapper courseMapper;

    @Transactional
    public void selectCourse(Long studentId, Long courseId) {
        Course course = courseMapper.selectById(courseId);
        checkCourseAvailability(course);
        checkTimeConflict(studentId, course);
        
        CourseSelection selection = new CourseSelection();
        selection.setStudentId(studentId);
        selection.setCourseId(courseId);
        selection.setSelectionTime(LocalDateTime.now());
        selection.setStatus("待确认");
        selectionMapper.insert(selection);
        
        course.setSelectedCount(course.getSelectedCount() + 1);
        courseMapper.update(course);
    }

    private void checkCourseAvailability(Course course) {
        if (course.getSelectedCount() >= course.getCapacity()) {
            throw new RuntimeException("课程容量已满");
        }
    }

    private void checkTimeConflict(Long studentId, Course newCourse) {
        List<Course> conflictingCourses = selectionMapper.findConflictingCourses(
            studentId, 
            newCourse.getStartTime(),
            newCourse.getEndTime());
        if (!conflictingCourses.isEmpty()) {
            throw new RuntimeException("检测到时间冲突课程：" + conflictingCourses.get(0).getCourseName());
        }
    }

    @Transactional
    public void cancelSelection(Long selectionId) {
        CourseSelection selection = selectionMapper.selectById(selectionId);
        Course course = courseMapper.selectById(selection.getCourseId());
        
        selection.setStatus("已退选");
        course.setSelectedCount(course.getSelectedCount() - 1);
        
        selectionMapper.update(selection);
        courseMapper.update(course);
    }
}