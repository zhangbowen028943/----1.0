package com.example.demo.controller;

import com.example.demo.model.Grade;
import com.example.demo.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('TEACHER')")
    public void recordGrade(@RequestBody Grade grade) {
        gradeService.recordGrade(grade);
    }

    @GetMapping("/{studentId}/{courseId}")
    @PreAuthorize("hasRole('STUDENT') and #studentId == authentication.principal.id")
    public Grade getGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        return gradeService.getStudentGrade(studentId, courseId);
    }
}