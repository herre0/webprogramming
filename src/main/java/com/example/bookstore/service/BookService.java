package com.example.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.bookstore.model.Book;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void updateBook(Book b) {
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).getTitle().equals(b.getTitle())){
                books.set(i, b);
                break;
            }
        }
    }   

    public void deleteBook(String title) {
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).getTitle().equals(title)){
                books.remove(i);
                break;
            }
        }
    }
    public Book getBookbyTitle(String title) {
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).getTitle().equals(title)){
                return books.get(i);
            }
        }
        return null;
    }
}
