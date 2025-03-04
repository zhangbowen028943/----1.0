package com.example.demo.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Attendance {
    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDateTime checkinTime;
    private String status; // 考勤状态：正常/迟到/缺勤
    private String location;
    private String deviceInfo;
}