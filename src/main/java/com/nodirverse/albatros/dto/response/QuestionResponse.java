package com.nodirverse.albatros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionResponse {
    private String name;
    private String phoneNumber;
    private String question;
}
