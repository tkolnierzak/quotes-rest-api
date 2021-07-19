package pl.decerto.tkolnierzak.restapiquotes.quote.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Quote {
    @Id
    @GeneratedValue
    private long id;
    private String author;
    @ElementCollection
    private List<String> contents;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public Quote(String author, List<String> contents) {
        this.author = author;
        this.contents = contents;
    }

    public Quote() {
    }

    public Quote(String author) {
        this.author = author;
    }
}
