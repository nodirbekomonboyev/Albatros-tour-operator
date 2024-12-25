package com.nodirverse.albatros.service;

import com.nodirverse.albatros.entity.Question;
import com.nodirverse.albatros.dto.request.QuestionRequest;
import com.nodirverse.albatros.dto.response.QuestionResponse;
import com.nodirverse.albatros.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;


    public String sendQuestion(QuestionRequest request) {
        questionRepository.save(modelMapper.map(request, Question.class));
        return "question sent!";
    }

    public List<QuestionResponse> getAll() {
        List<Question> list = questionRepository.findAll();
        List<QuestionResponse> responses = new ArrayList<>();
        list.forEach((item) -> responses.add(modelMapper.map(item, QuestionResponse.class)));
        return responses;
    }
}
