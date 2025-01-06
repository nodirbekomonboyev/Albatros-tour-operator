package com.nodirverse.albatros.dto.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsResponse {
    private UUID id;
    private String title;
    private String text;
    private String image;
    private String link;
    private String content;
}
