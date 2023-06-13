package com.example.simplespringapp.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookAPIController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("api/books/add")
    public String addBook(String title, double price) {
        Book book = new Book();
        book.setTitle(title);
        book.setPrice(price);
        bookRepository.save(book);
        return "Book added";
    }

    @GetMapping("api/books/list")
    public Iterable<Book> listBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("api/books/find/{id}")
    public Book findBook(@PathVariable Integer id) {
        return bookRepository.findBookById(id);
    }
}
