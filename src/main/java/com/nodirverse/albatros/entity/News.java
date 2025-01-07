package com.nodirverse.albatros.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "news")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class News extends BaseEntity {
    private String title;
    private String text;
    private String image;
    private String link;
    private String content;
}
