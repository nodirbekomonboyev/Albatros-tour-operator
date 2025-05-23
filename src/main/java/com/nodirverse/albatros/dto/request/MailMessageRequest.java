package com.nodirverse.albatros.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MailMessageRequest {
    private String receiver;
    private String subject;
    private String message;

}
