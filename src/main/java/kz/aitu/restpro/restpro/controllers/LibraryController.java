package kz.aitu.restpro.restpro.controllers;

import kz.aitu.restpro.restpro.entitiess.Book;
import kz.aitu.restpro.restpro.entitiess.Library;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return Library.getAllBooksFromDB();
    }

    @GetMapping("/books/{title}")
    public Book getBook(@PathVariable String title) {
        Book book = Library.getBookByTitleDB(title);
        if (book == null) {
            throw new RuntimeException("Book not found: " + title);
        }
        return book;
    }

    @PostMapping("/books")
    public String createBook(@RequestBody Book book) {
        Library.addBookToDB(book);
        return "Book added!";
    }

    @PutMapping("/books/{title}")
    public String updateAvailability(@PathVariable String title, @RequestBody Book body) {
        Library.updateBookAvailabilityDB(title, body.isAvailable());
        return "Book availability updated!";
    }

    @DeleteMapping("/books/{title}")
    public String deleteBook(@PathVariable String title) {
        Library.deleteBookByTitleDB(title);
        return "Book deleted!";
    }
}
