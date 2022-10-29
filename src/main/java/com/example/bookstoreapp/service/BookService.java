package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.exception.BookStoreCustomException;
import com.example.bookstoreapp.model.BookData;
import com.example.bookstoreapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public BookData addBook(BookDTO bookDTO) {
        return bookRepository.save(new BookData(bookDTO));
    }
    @Override
    public List<BookData> getBookList() {
        return bookRepository.findAll();
    }
    @Override
    public BookData getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookStoreCustomException("Book with id " + bookId + " not found"));
    }
    @Override
    public BookData updateBookQuantity(Long bookId, int bookQuantity) {
        BookData bookData = this.getBookById(bookId);
        bookData.setBookQuantity(bookQuantity);
        return bookRepository.save(bookData);
    }
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}