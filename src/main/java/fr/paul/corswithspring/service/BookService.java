package fr.paul.corswithspring.service;

import fr.paul.corswithspring.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {

    List<Book> library = new ArrayList<>();

    private BookService(){
        library.add(new Book(1,"The Lord Of The Rings", "John Tolkien", 5));
        library.add(new Book(2,"Le Petit Prince", "Antoine de Saint-Exupery", 5));
        library.add(new Book(3,"Harry Potter and the Philosopher’s Stone", "J.K. Rowling", 4));
        library.add(new Book(4,"The Da Vinci Code", "Dan Brown", 3));
        library.add(new Book(5,"Harry Potter and the Half-Blood Prince", "J.K. Rowling", 2));
    }

    public List<Book> getBooks() {
        return library;
    }

    public Book getBook(Integer id) {
        return library.stream().filter(book -> book.getId().equals(id)).findFirst().orElseThrow();
    }

    public List<Book> getBooksWithRatingMoreThan(Integer rating) {
        return library.stream().filter(book -> book.getRating() > rating).collect(Collectors.toList());
    }

    public List<Book> getBooksWithRatingEquals(Integer rating) {
        return library.stream().filter(book -> book.getRating().equals(rating)).collect(Collectors.toList());
    }

    public List<Book> getBooksWithRatingLessThan(Integer rating) {
        return library.stream().filter(book -> book.getRating() < rating).collect(Collectors.toList());
    }

    public List<Book> getBooksWithTitleContain(String title) {
        return library.stream().filter(book -> book.getTitle().contains(title)).collect(Collectors.toList());
    }

    public List<Book> getBooksWithAuthorContain(String author) {
        return library.stream().filter(book -> book.getAuthor().contains(author)).collect(Collectors.toList());
    }

}
