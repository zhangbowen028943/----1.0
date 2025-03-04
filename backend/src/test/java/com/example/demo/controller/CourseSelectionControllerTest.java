package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourseSelectionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "STUDENT", username = "student1")
    void shouldSelectCourse() throws Exception {
        mockMvc.perform(post("/api/courses/1001/select"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "STUDENT", username = "student1")
    void shouldPreventDuplicateSelection() throws Exception {
        // 首次选课
        mockMvc.perform(post("/api/courses/1001/select"));

        // 重复选课
        mockMvc.perform(post("/api/courses/1001/select"))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = "STUDENT", username = "student1")
    void shouldHandleFullCapacity() throws Exception {
        // 模拟选满50个学生
        for (int i = 0; i < 50; i++) {
            mockMvc.perform(post("/api/courses/1001/select")
                    .with(request -> {
                        request.setRemoteUser("student" + (i + 2));
                        return request;
                    }));
        }

        // 第51次选课
        mockMvc.perform(post("/api/courses/1001/select"))
                .andExpect(status().isBadRequest());
    }
}