package ch.hftm.blogproject.model.entity;

import java.time.ZonedDateTime;
import java.util.List;

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
    @Column(name = "blogpost_id")
    private Long id;
    // @Column(nullable = false)
    private String title;
    // @Column(columnDefinition = "TEXT")
    // @Column(nullable = false)
    private String content;
    private String creator;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastChangedAt;
    
    @OneToMany(mappedBy = "blogpost", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments;

    // Constructor for creating new blog posts
    // public BlogPost(String title, String content, Long accountId) {
    //     this.title = title;
    //     this.content = content;
    //     this.accountId = accountId;
    //     this.createdAt = ZonedDateTime.now();
    //     this.lastChangedAt = ZonedDateTime.now();
    // }
}
