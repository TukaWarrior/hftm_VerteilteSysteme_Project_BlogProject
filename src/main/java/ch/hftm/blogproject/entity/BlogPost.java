package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Defines the Blog entity representing a blog post with its properties

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private ZonedDateTime createdAt;
    private ZonedDateTime changedAt;
    private Long accountId;
    
    @OneToMany (mappedBy = "blogPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    // Constructor to create new blogPosts.
    public BlogPost (String title, String content, Account account) {
        this.title = title;
        this.content = content;
        // this.account = account;
        this.createdAt = ZonedDateTime.now();
        this.changedAt = ZonedDateTime.now();
    }

        // Constructor to create new blogPosts.
        public BlogPost (String title, String content, Long accountId) {
            this.title = title;
            this.content = content;
            this.accountId = accountId;
            this.createdAt = ZonedDateTime.now();
            this.changedAt = ZonedDateTime.now();
        }
}

    // Constructor used in Blog DTO
    // public BlogPost (String title, String content) {
    //     this.title = title;
    //     this.content = content;
    // }