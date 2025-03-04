package com.example.demo.controller;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("STUDENT");

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"password\",\"role\":\"STUDENT\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "testuser")
    void shouldGetCurrentUser() throws Exception {
        mockMvc.perform(get("/api/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldPreventDuplicateUsername() throws Exception {
        // 先创建用户
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"duplicate\",\"password\":\"pass\",\"role\":\"STUDENT\"}"));

        // 尝试重复创建
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"duplicate\",\"password\":\"pass\",\"role\":\"STUDENT\"}"))
                .andExpect(status().isConflict());
    }
}