package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @GetMapping("/statistics")
    public ResponseEntity<?> getEvaluationStatistics() {
        // TODO: 实现教学评价统计逻辑
        return ResponseEntity.ok().build();
    }
}