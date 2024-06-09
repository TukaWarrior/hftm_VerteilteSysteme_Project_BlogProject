package ch.hftm.blogproject.entity;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// Defines the Blog entity representing a blog post with its properties

@Entity
public class Blog {

    @Id 
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private ZonedDateTime date;
    
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }


    public String getContent() {
        return this.content;
    }

    public ZonedDateTime getDate() {
        return this.date;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Blog() {
    }

    public Blog(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = ZonedDateTime.now();
    }
}
