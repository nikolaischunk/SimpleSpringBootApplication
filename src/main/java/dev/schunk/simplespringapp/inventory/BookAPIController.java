package dev.schunk.simplespringapp.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookAPIController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("api/books/add")
    public String addBook(@RequestParam String title, @RequestParam(required = false) Double price) {
        if (title == null || title.isEmpty()) {
            return "Title is required";
        }
        if (price == null) price = 0.0;
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

    @GetMapping("api/books/find")
    public Book findBookByTitle(@RequestParam String title) {
        return bookRepository.findBookByTitleContainsIgnoreCase(title);
    }

    @DeleteMapping("api/books/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return "Book deleted";
    }
}
