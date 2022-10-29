package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
    @Query(value = "select * from user_registration where email = :email and password = :password", nativeQuery = true)
    UserRegistration loginValidation(String email, String password);
    @Query(value = "select * from user_registration where email = :email", nativeQuery = true)
    UserRegistration getUserByEmail(String email);
}