package com.example.demo.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Course {
    private Long id;
    private String courseName;
    private String courseCode;
    private Long teacherId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer capacity;
    private Integer selectedCount = 0;
    private String classroom;
    private String description;
}