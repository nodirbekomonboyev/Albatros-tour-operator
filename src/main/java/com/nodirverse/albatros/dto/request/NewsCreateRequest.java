package com.nodirverse.albatros.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsCreateRequest {
    private String title;
    private String text;
    private String link;
}
