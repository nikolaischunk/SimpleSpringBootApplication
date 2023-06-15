package dev.schunk.simplespringapp.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(BookAPIController.class) //enable Spring MVC testing and only load the necessary components for testing the BookAPIController.
class BookAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean //annotation is used to mock the BookRepository dependency.
    private BookRepository bookRepository;

    private List<Book> books;

    //add books to the database before each test
    @BeforeEach
    void setUp() {
        books = new ArrayList<>();

        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Book 1");
        book1.setPrice(10.99);
        books.add(book1);

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("a fancy book");
        book2.setPrice(19.99);
        books.add(book2);
    }

    @Test
    void testAddBook() throws Exception {
        String title = "Test Book";
        Double price = 9.99;

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/books/add").param("title", title).param("price", String.valueOf(price)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("Book added"));

        //check if entry is saved
        Mockito.verify(bookRepository).save(Mockito.any(Book.class));
    }

    @Test
    void testListBooks() throws Exception {
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/list")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Book 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(10.99));
    }

    @Test
    void findBook() throws Exception {
        Mockito.when(bookRepository.findBookById(1)).thenReturn(books.get(0));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/find/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Book 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10.99));
    }

    @Test
    void testFindBookByTitle() throws Exception {
        String title = "a fancy book";
        Mockito.when(bookRepository.findBookByTitleContainsIgnoreCase(title)).thenReturn(books.get(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/find")
                        .param("title", title))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(19.99));
    }

    @Test
    void deleteBook() throws Exception {
        int id = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Book deleted"));
        Mockito.verify(bookRepository).deleteById(id);
    }
}