package com.example.lab3.service;

import com.example.lab3.Book;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public BookService() {
        // Thêm sẵn 3 cuốn sách theo yêu cầu của bạn
        addBook(new Book(null, "Lập trình Java", "Nguyễn Văn A"));
        addBook(new Book(null, "Spring Boot Cơ Bản", "Trần Thị B"));
        addBook(new Book(null, "Thymeleaf Guide", "Lê Văn C"));
    }

    public List<Book> getAllBooks() { return books; }

    public void addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
    }

    public Optional<Book> getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public void updateBook(Book updatedBook) {
        getBookById(updatedBook.getId()).ifPresent(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
        });
    }

    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}