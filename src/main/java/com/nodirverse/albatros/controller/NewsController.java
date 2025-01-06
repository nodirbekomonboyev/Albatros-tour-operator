package com.nodirverse.albatros.controller;

import com.nodirverse.albatros.dto.request.NewsCreateRequest;
import com.nodirverse.albatros.dto.response.EmployeeResponse;
import com.nodirverse.albatros.dto.response.NewsResponse;
import com.nodirverse.albatros.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping("create")
    public ResponseEntity<String> createNews(@RequestBody NewsCreateRequest request){
        return ResponseEntity.ok(newsService.create(request));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<NewsResponse>> getAllNews(){
        return ResponseEntity.ok(newsService.getAll());
    }

    @PutMapping("update")
    public ResponseEntity<String> updateNews(
            @RequestParam(value = "id") UUID id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "content", required = false) String content
    ){
        return ResponseEntity.ok(newsService.update(id, title, text, link, content));
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@RequestParam(value = "id") UUID id){
        return ResponseEntity.ok(newsService.delete(id));
    }
}
