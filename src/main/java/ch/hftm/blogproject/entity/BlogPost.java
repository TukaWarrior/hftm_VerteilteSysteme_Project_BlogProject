package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String content;
    
    private ZonedDateTime createdAt;
    private ZonedDateTime changedAt;
    
    private Long accountId;

    // Constructor for creating new blog posts
    public BlogPost(String title, String content, Long accountId) {
        this.title = title;
        this.content = content;
        this.accountId = accountId;
        this.createdAt = ZonedDateTime.now();
        this.changedAt = ZonedDateTime.now();
    }
}
