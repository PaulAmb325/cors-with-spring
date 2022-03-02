package fr.paul.corswithspring.controller;


import fr.paul.corswithspring.model.Book;
import fr.paul.corswithspring.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Every route of this controller can be called freely
@CrossOrigin enable CORS for every origins
 */

@RestController
@RequestMapping("/public-api")
@CrossOrigin
public class PublicBookController {

    BookService bookService;

    public PublicBookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok()
                .body(bookService.getBooks());
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBooks(@PathVariable Integer id){
        return ResponseEntity.ok()
                .body(bookService.getBook(id));
    }
}
