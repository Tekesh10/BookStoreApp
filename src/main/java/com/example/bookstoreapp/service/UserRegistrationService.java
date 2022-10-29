package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.UserRegistrationDTO;
import com.example.bookstoreapp.exception.BookStoreCustomException;
import com.example.bookstoreapp.model.UserRegistration;
import com.example.bookstoreapp.repository.UserRegistrationRepository;
import com.example.bookstoreapp.util.EmailSender;
import com.example.bookstoreapp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserRegistrationService implements IUserRegistrationService {
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private EmailSender emailSender;
    public String createUserRegistration(UserRegistrationDTO userRegistrationDTO){
        UserRegistration user = userRegistrationRepository.save(new UserRegistration(userRegistrationDTO));
        String token = jwtToken.createToken(user.getUserId());
        emailSender.sendEmail(user.getEmail(), "Registration confirmation", "User "+user.getFirstName()+" "
                +user.getLastName()+". Thank you for Registering."+'\n'+user);
        return token;
    }
    @Override
    public List<UserRegistration> getAllUserData() {
        if (userRegistrationRepository.findAll().isEmpty()) {
            System.out.println("No data found");
            throw new BookStoreCustomException("No User found in database.");
        } else return userRegistrationRepository.findAll();
    }
    @Override
    public UserRegistration getUserDataByToken(String token) {
        return userRegistrationRepository.findById(jwtToken.decodeToken(token)).orElseThrow(()
                -> new BookStoreCustomException("User not found"));
    }
    @Override
    public UserRegistration updateUserRegistration(String token, UserRegistrationDTO userRegistrationDTO) {
        UserRegistration userRegistration = userRegistrationRepository.findById(jwtToken.decodeToken(token)).orElse(null);
        if (userRegistration != null) {
            userRegistration.setFirstName(userRegistrationDTO.getFirstName());
            userRegistration.setLastName(userRegistrationDTO.getLastName());
            userRegistration.setEmail(userRegistrationDTO.getEmail());
            userRegistration.setPassword(userRegistrationDTO.getPassword());
            userRegistration.setAddress(userRegistrationDTO.getAddress());
        }
        assert userRegistration != null;
        return userRegistrationRepository.save(userRegistration);
    }
    public void deleteUser(String token) {
        userRegistrationRepository.delete(this.getUserDataByToken(token));
    }
    @Override
    public UserRegistration userLogin(String email, String password) {
        return userRegistrationRepository.loginValidation(email, password);
    }
    @Override
    public UserRegistration forgotUserPassword(Long userId, String email) {
        UserRegistration userRegistrationData = userRegistrationRepository.getUserByEmail(email);
        if (userRegistrationData != null)
            return userRegistrationData;
        else
            throw new BookStoreCustomException("User with email id " + userId + " not found");
    }
}