package com.nodirverse.albatros.service;


import com.nodirverse.albatros.dto.request.MailMessageRequest;
import com.nodirverse.albatros.dto.response.SendMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MailMessageService {

    @Autowired
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    protected SendMessageResponse sendMessage(MailMessageRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setSubject(request.getSubject());
        message.setTo(request.getReceiver());
        message.setText(request.getMessage());
        javaMailSender.send(message);
        return new SendMessageResponse(request, LocalDateTime.now());
    }

    public SendMessageResponse sendVerification(MailMessageRequest request) {
        return sendMessage(request);
    }
}
