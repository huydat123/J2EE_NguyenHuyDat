package com.example.Bai2.Service;

import com.example.Bai2.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    public List<Book> getAllBooks() {
        return books;
    }
    public Book getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id)
                .findFirst().orElse(null);
    }
    public void addBook(Book book) {
        books.add(book);
    }
    public void updateBook(int id, Book updatedBook) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                return;
            }
        }
    }

    // Xóa sách theo ID
    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}