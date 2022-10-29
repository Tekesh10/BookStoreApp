package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.dto.UserRegistrationDTO;
import com.example.bookstoreapp.model.UserRegistration;
import com.example.bookstoreapp.service.IUserRegistrationService;
import com.example.bookstoreapp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstore/user")
public class UserRegistrationController {
    @Autowired
    private IUserRegistrationService iUserRegistrationService;
    @Autowired
    JWTToken jwtToken;
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> createUserRegistrationData(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Data Added Successfully and email sent to the User", iUserRegistrationService.createUserRegistration(userRegistrationDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllUserData() {
        List<UserRegistration> usersData = iUserRegistrationService.getAllUserData();
        System.out.println(usersData.toString());
        ResponseDTO responseDTO = new ResponseDTO("Users: ", usersData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getBy")
    public ResponseEntity<ResponseDTO> getUserData(@RequestHeader String token){
        long Userid = jwtToken.decodeToken(token);
        ResponseDTO responseDTO = new ResponseDTO("Data retrieved successfully for the ID: "+Userid, iUserRegistrationService.getUserDataByToken(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateUserRegistrationData(@RequestHeader String token, @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Updated User Data : ", iUserRegistrationService.updateUserRegistration(token, userRegistrationDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteUserRegistrationData(@RequestHeader String token) {
        iUserRegistrationService.deleteUser(token);
        ResponseDTO responseDTO = new ResponseDTO("User Registration Data Deleted Successfully", "Deleted");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestParam String email, @RequestParam("password") String password) {
        ResponseDTO responseDTO = new ResponseDTO("Login Successful", iUserRegistrationService.userLogin(email, password));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/forgotPassword/{userId}")
    public ResponseEntity<ResponseDTO> forgotPassword(@PathVariable("userId") Long userId,@RequestParam("email") String email) {
        UserRegistration userRegistrationData = iUserRegistrationService.forgotUserPassword(userId,email);
        ResponseDTO responseDTO = new ResponseDTO("User Data For "+userId+" :", userRegistrationData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}