package com.example.demo.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CourseSelection {
    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDateTime selectionTime;
    private String status; // 选课状态：成功/待确认/已退选
}