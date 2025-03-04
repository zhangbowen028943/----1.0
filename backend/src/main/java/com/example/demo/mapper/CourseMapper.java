package com.example.demo.mapper;

import com.example.demo.model.Course;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CourseMapper {
    @Insert("INSERT INTO course(course_name, course_code, teacher_id, start_time, end_time, capacity, classroom, description) " +
            "VALUES(#{courseName}, #{courseCode}, #{teacherId}, #{startTime}, #{endTime}, #{capacity}, #{classroom}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Course course);

    @Update("UPDATE course SET course_name=#{courseName}, teacher_id=#{teacherId}, start_time=#{startTime}, " +
            "end_time=#{endTime}, capacity=#{capacity}, classroom=#{classroom}, description=#{description} WHERE id=#{id}")
    void update(Course course);

    @Select("SELECT * FROM course WHERE id = #{id}")
    Course selectById(Long id);

    @Delete("DELETE FROM course WHERE id=#{id}")
    void delete(Long id);
}