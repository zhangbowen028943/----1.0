package com.example.demo.mapper;

import com.example.demo.model.Attendance;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AttendanceMapper {
    @Insert("INSERT INTO attendance(student_id, course_id, checkin_time, status, location, device_info) " +
            "VALUES(#{studentId}, #{courseId}, #{checkinTime}, #{status}, #{location}, #{deviceInfo})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Attendance attendance);

    @Update("UPDATE attendance SET status=#{status}, location=#{location} WHERE id=#{id}")
    void updateStatus(Attendance attendance);

    @Select("SELECT * FROM attendance WHERE student_id = #{studentId} AND course_id = #{courseId}")
    Attendance findByStudentAndCourse(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    @Delete("DELETE FROM attendance WHERE id=#{id}")
    void delete(Long id);
}