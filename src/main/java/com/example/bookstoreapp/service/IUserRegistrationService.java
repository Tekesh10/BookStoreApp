package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.UserRegistrationDTO;
import com.example.bookstoreapp.model.UserRegistration;

import java.util.List;

public interface IUserRegistrationService {
    String createUserRegistration(UserRegistrationDTO userRegistrationDTO);
    List<UserRegistration> getAllUserData();
    UserRegistration getUserDataByToken(String token);
    UserRegistration updateUserRegistration(String token, UserRegistrationDTO userRegistrationDTO);
    void deleteUser(String token);
    UserRegistration userLogin(String email, String password);
    UserRegistration forgotUserPassword(Long userId, String email);
}