package dev.schunk.simplespringapp.inventory;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findBookById(Integer id);

    Book findBookByTitleContainsIgnoreCase(String title);
}
