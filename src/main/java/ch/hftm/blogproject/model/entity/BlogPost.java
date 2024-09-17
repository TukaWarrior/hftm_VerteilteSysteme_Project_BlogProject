package ch.hftm.blogproject.model.entity;

import java.time.ZonedDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Long blogPostID;
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
}
