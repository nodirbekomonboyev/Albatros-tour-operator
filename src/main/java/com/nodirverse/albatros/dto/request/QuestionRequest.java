package com.nodirverse.albatros.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionRequest {
    private String name;
    private String phoneNumber;
    private String question;
}
