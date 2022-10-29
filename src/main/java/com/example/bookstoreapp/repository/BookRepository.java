package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookData, Long> {
}