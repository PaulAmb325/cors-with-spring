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
This controller have specified cors settings for every route
 */
@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    /*
    The @CrossOrigin enable the use of a white list
     */
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
    @GetMapping("/rateInf/{rating}")
    public ResponseEntity<List<Book>> getBooksWithRatingLessThan(@PathVariable Integer rating){
        return ResponseEntity.ok()
                .body(bookService.getBooksWithRatingLessThan(rating));
    }

    /*
    It is also possible to set the origin to match certain patterns

    Exemple from the doc:
    https://*.domain1.com -- domains ending with domain1.com
    https://*.domain1.com:[8080,8081] -- domains ending with domain1.com on port 8080 or port 8081
    https://*.domain1.com:[*] -- domains ending with domain1.com on any port, including the default port
     */
    @CrossOrigin(originPatterns = {"http://localhost:[*]"}, allowCredentials =  "true")
    @GetMapping("/rateEq/{rating}")
    public ResponseEntity<List<Book>> getBooksWithRatingEquals(@PathVariable Integer rating){
        return ResponseEntity.ok()
                .body(bookService.getBooksWithRatingEquals(rating));
    }

    /*
    The @CrossOrigin can also load the origin from a config file
    It can also be configured to allow credentials
     */
    @CrossOrigin(origins = {"${cors.allowedOriginsInController}"}, allowCredentials =  "true")
    @GetMapping("/rateSup/{rating}")
    public ResponseEntity<List<Book>> getBooksWithRatingMoreThan(@PathVariable Integer rating){
        return ResponseEntity.ok()
                .body(bookService.getBooksWithRatingMoreThan(rating));
    }

}
