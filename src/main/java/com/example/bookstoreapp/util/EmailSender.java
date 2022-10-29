package com.example.bookstoreapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gtcsantekesh@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        System.out.println("Mail sent to the User Successfully");
    }
}