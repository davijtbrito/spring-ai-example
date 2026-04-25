package com.example.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.services.VectorStoreService;


@RestController
@RequestMapping("/api/vector")
public class VectorController {

    private final VectorStoreService vectorStoreService;

    public VectorController(VectorStoreService vectorStoreService) {
        this.vectorStoreService = vectorStoreService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test completed.";
    }

    @GetMapping("/query")
    public String query(@RequestParam String q) {
        return vectorStoreService.answerBusinessQuery(q);
    }

}