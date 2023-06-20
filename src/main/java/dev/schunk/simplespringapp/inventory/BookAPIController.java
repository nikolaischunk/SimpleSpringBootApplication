package dev.schunk.simplespringapp.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books/")
public class BookAPIController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping(value = "add")
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

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonProperty
    public ResponseEntity<Iterable<Book>> listBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping(value = "find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Book findBook(@PathVariable Integer id) {
        return bookRepository.findBookById(id);
    }

    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Book findBookByTitle(@RequestParam String title) {
        return bookRepository.findBookByTitleContainsIgnoreCase(title);
    }

    @DeleteMapping("delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return "Book deleted";
    }
}
