package com.energymonitor.controller;

import com.energymonitor.entities.TestEntity;
import com.energymonitor.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test-db")
public class TestDBController {

    @Autowired
    private TestRepository testRepository;

    @PostMapping("/create")
    public ResponseEntity<TestEntity> createTest(@RequestBody TestEntity test) {
        return ResponseEntity.ok(testRepository.save(test));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTests() {
        return ResponseEntity.ok(testRepository.findAll());
    }
}
