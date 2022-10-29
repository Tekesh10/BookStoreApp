package com.example.bookstoreapp.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CartData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long cartId;
    @JoinColumn(name = "user_id")
    @OneToOne()
    private UserRegistration user;
    @JoinColumn(name = "Book_id")
    @ManyToOne()
    private BookData book;
    private int quantity;
    private double totalPrice;
    public CartData(UserRegistration userRegistration, BookData bookData, int quantity, double totalPrice){
        this.user = userRegistration;
        this.book = bookData;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}