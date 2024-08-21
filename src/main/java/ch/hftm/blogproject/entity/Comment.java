// This class is currently unused. It serves as a WIP idea t oimplement comments under a blog post.
package ch.hftm.blogproject.entity;

import java.time.ZonedDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // private Long blogId; // May not be needed
    @NotNull @NotBlank
    private String content;
    private Long accountId;
    // private Long likes;
    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime changedAt = ZonedDateTime.now();

    // Constructor used in Comment DTO
    public Comment (String content) {
        this.content = content;
    }

    // public Comment (Long blogId, String content) {
    //     this.blogId = blogId;
    //     this.content = content;
    // }
}



    // Foreign key reference to the blog
    // @ManyToOne
    // @JoinColumn(name = "blog_id", nullable = false)
    // private Blog blog;
