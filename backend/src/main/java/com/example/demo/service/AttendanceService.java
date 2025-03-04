package com.example.demo.service;

import com.example.demo.mapper.AttendanceMapper;
import com.example.demo.model.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceMapper attendanceMapper;

    public void checkIn(Long studentId, Long courseId, String location, String deviceInfo) {
        Attendance attendance = new Attendance();
        attendance.setStudentId(studentId);
        attendance.setCourseId(courseId);
        attendance.setCheckinTime(LocalDateTime.now());
        attendance.setStatus("正常");
        attendance.setLocation(location);
        attendance.setDeviceInfo(deviceInfo);
        attendanceMapper.insert(attendance);
    }

    public void updateAttendanceStatus(Long attendanceId, String status) {
        Attendance attendance = attendanceMapper.findByStudentAndCourse(null, null); // 实际应根据ID查询
        attendance.setStatus(status);
        attendanceMapper.updateStatus(attendance);
    }

    public Attendance getAttendanceRecord(Long studentId, Long courseId) {
        return attendanceMapper.findByStudentAndCourse(studentId, courseId);
    }
}