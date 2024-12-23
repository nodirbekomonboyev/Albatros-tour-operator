package com.nodirverse.albatros.controller;


import com.nodirverse.albatros.entity.dto.request.MailMessageRequest;
import com.nodirverse.albatros.entity.dto.response.SendMessageResponse;
import com.nodirverse.albatros.service.MailMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class MailController {

    private final MailMessageService mailMessageService;

    @PostMapping("/send-code")
    public SendMessageResponse sendMessage(@RequestBody MailMessageRequest request){
        return mailMessageService.sendVerification(request);
    }
}
