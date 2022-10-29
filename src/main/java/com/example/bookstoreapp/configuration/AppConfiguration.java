package com.example.bookstoreapp.configuration;

import com.example.bookstoreapp.util.EmailSender;
import com.example.bookstoreapp.util.JWTToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public JWTToken jwtToken() {
        return new JWTToken();
    }
    @Bean
    public EmailSender emailSender() {
        return new EmailSender();
    }
}