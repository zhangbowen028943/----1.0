package com.example.demo.mapper;

import com.example.demo.model.Grade;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GradeMapper {
    @Insert("INSERT INTO grade(student_id, course_id, score, remark) " +
            "VALUES(#{studentId}, #{courseId}, #{score}, #{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Grade grade);

    @Update("UPDATE grade SET score=#{score}, remark=#{remark} WHERE id=#{id}")
    void update(Grade grade);

    @Select("SELECT * FROM grade WHERE student_id = #{studentId} AND course_id = #{courseId}")
    Grade findByStudentAndCourse(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    @Delete("DELETE FROM grade WHERE id=#{id}")
    void delete(Long id);
}