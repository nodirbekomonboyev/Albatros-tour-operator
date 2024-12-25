package com.nodirverse.albatros.controller;

import com.nodirverse.albatros.dto.request.QuestionRequest;
import com.nodirverse.albatros.dto.response.QuestionResponse;
import com.nodirverse.albatros.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @PostMapping("/send")
    public ResponseEntity<String> sendQuestion(@RequestBody QuestionRequest request){
        return ResponseEntity.ok(questionService.sendQuestion(request));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<QuestionResponse>> getAll(){
        return ResponseEntity.ok(questionService.getAll());
    }
}
