package com.example.bookstoreapp.model;
import com.example.bookstoreapp.dto.UserRegistrationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_registration")
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    public UserRegistration(UserRegistrationDTO userRegistrationDTO) {
        this.firstName = userRegistrationDTO.firstName;
        this.lastName = userRegistrationDTO.lastName;
        this.email = userRegistrationDTO.email;
        this.password = userRegistrationDTO.password;
        this.address = userRegistrationDTO.address;
    }
}