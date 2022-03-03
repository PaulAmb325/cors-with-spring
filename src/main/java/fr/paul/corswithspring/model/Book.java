package fr.paul.corswithspring.model;

public class Book {

    private final Integer id;

    private final String title;

    private final String author;

    private final Integer rating;

    public Book(Integer id, String title, String author, Integer rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getId() {
        return id;
    }

}
