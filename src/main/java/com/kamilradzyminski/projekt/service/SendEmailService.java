package com.kamilradzyminski.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String body, String topic){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("personappsjavaapp@gmail.com");
        message.setTo(to);
        message.setSubject(topic);
        message.setText(body);
        javaMailSender.send(message);
    }
}