package com.example.bookstoreapp.exception;

public class BookStoreCustomException extends RuntimeException{
    public BookStoreCustomException(String message) {
        super(message);
    }
}