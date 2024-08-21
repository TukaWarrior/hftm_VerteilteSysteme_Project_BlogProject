package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Defines the Blog entity representing a blog post with its properties

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotBlank @Size(min = 5, message = "Title needs at least 5 characters")
    private String title;
    @NotNull @NotBlank
    private String content;
    // private Long likes;
    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime changedAt = ZonedDateTime.now();

    @ManyToOne
    private Account account;
    
    @OneToMany
    private List<Comment> comments;

    // Constructor used in Blog DTO
    public BlogPost (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
