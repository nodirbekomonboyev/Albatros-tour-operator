package com.nodirverse.albatros.dto.response;

import com.nodirverse.albatros.dto.request.MailMessageRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SendMessageResponse {
    private MailMessageRequest dto;
    private LocalDateTime time;
}
