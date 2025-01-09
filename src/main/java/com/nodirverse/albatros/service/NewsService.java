package com.nodirverse.albatros.service;

import com.nodirverse.albatros.dto.request.NewsCreateRequest;
import com.nodirverse.albatros.dto.response.NewsResponse;
import com.nodirverse.albatros.entity.Hotel;
import com.nodirverse.albatros.entity.News;
import com.nodirverse.albatros.exception.DataNotFoundException;
import com.nodirverse.albatros.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;

    public String create(NewsCreateRequest request) {
        newsRepository.save(modelMapper.map(request, News.class));
        return "News creation successful";
    }

    public List<NewsResponse> getAll() {
        List<NewsResponse> list = new ArrayList<>();
        newsRepository.findAll().forEach(news -> {
            NewsResponse newsResponse = modelMapper.map(news, NewsResponse.class);
            newsResponse.setId(news.getId());
            list.add(newsResponse);
        });
        return list;
    }


    public String update(UUID id, String title, String text, String link) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("news not found!")
        );

        if(title != null){
            news.setTitle(title);
        }
        if(text != null){
            news.setText(text);
        }
        if(link != null){
            news.setLink(link);
        }
        newsRepository.save(news);
        return "News updated";
    }

    public String delete(UUID id) {
        newsRepository.deleteById(id);
        return "News deleted";
    }

    public void updateImageUrl(UUID id, String imageUrl) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found with id: " + id));

        news.setImageUrl(imageUrl);
        newsRepository.save(news);
    }
}
