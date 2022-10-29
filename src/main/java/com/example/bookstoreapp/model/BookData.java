package com.example.bookstoreapp.model;
import com.example.bookstoreapp.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book_data")
public class BookData {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookImage;
    private int bookPrice;
    private int bookQuantity;
    public BookData(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.bookAuthor = bookDTO.bookAuthor;
        this.bookDescription = bookDTO.bookDescription;
        this.bookImage = bookDTO.bookImage;
        this.bookPrice = bookDTO.bookPrice;
        this.bookQuantity = bookDTO.bookQuantity;
    }
}