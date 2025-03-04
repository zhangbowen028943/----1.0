package com.example.demo.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Grade {
    private Long id;
    private Long studentId;
    private Long courseId;
    private Double score;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String remark;
}