package com.example.bookstoreapp.model;

import com.example.bookstoreapp.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table(name = "order_table")
public @Data class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @JoinColumn(name = "cart_id")
    @OneToOne(cascade = CascadeType.ALL)
    private CartData cartData;
    private String address;
    private LocalDate orderDate = LocalDate.now();
    private boolean cancel;
    public OrderData(CartData cartData,OrderDTO orderDTO) {
        this.cartData = cartData;
        this.address = orderDTO.address;
        this.orderDate = getOrderDate();
    }
}