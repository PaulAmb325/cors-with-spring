package fr.paul.corswithspring.controller;

import fr.paul.corswithspring.model.Book;
import fr.paul.corswithspring.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
This Controller is configured by the Bean in the class CorsConfiguration
*/
@RestController
@RequestMapping("/conf-api")
public class ConfBookController {

    private final BookService bookService;

    public ConfBookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity<List<Book>> getBooksWithTitleContain(@PathVariable String title){
        return ResponseEntity.ok()
                .body(bookService.getBooksWithTitleContain(title));
    }

    @GetMapping("/searchByAuthor/{author}")
    public ResponseEntity<List<Book>> getBooksWithAuthorContain(@PathVariable String author){
        return ResponseEntity.ok()
                .body(bookService.getBooksWithAuthorContain(author));
    }
}
