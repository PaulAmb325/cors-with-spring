package fr.paul.corswithspring.model;

public class Book {

    private Integer id;

    private String title;

    private String author;

    private Integer rating;

    public Book(Integer id, String title, String author, Integer rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
