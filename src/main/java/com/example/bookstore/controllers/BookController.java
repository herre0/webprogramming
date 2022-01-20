package com.example.bookstore.controllers;

import com.example.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.bookstore.model.Book;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String displayBooks(Model model) {
        var books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books.html";
    }

    // @PostMapping("/books")
    // public RedirectView saveBook(@RequestParam String title, @RequestParam String author, @RequestParam String publisher, 
    //     @RequestParam double price, Model model) {
    //     Book b = new Book(title, author, publisher, price);
    //     bookService.addBook(b);

    //     // var books = bookService.getAllBooks();
    //     // model.addAttribute("books", books);
    //     // return "books.html";
    //     return new RedirectView("/books");
    // }

    // @RequestMapping("/newbook")
    // public String displayNewBook() {
    //     return "newbook.html";
    // }

    @PostMapping("/newbook")
    public RedirectView saveBook(@ModelAttribute Book book, Model model) {
        bookService.addBook(book);

        return new RedirectView("/books");
    }

    @RequestMapping("/newbook")
    public String displayNewBook(Model model) {
        model.addAttribute("book", new Book("", "", "", 0.0));
        return "bookform.html";
    }

    @PostMapping("/deletebook")
    public String deleteBook(@RequestParam String title, Model model) {
        var books = bookService.getAllBooks();
        model.addAttribute("books", books);
        bookService.deleteBook(title);
        return "books.html";
    }

    @RequestMapping("/updatebook")
    public String displayBook(@RequestParam String title, Model model) {        
        model.addAttribute("book", bookService.getBookbyTitle(title));
        return "bookupdate.html";
    }

    @PostMapping("/updatebook")
    public RedirectView updateBook(@ModelAttribute Book book, Model model) {
        bookService.updateBook(book);

        return new RedirectView("/books");
    }
}
