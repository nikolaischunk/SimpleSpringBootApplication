package dev.schunk.simplespringapp.inventory;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findBookById(Integer id);

    List<Book> findBookByTitleContainsIgnoreCase(String title);
}
