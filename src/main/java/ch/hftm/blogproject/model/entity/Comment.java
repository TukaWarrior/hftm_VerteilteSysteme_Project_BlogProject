package ch.hftm.blogproject.model.entity;

import java.time.ZonedDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;
    // @Column(nullable = false)
    private String content;
    private String creator;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastChangedAt;
    // private Long accountId;

    @ManyToOne
    @JoinColumn(name = "blogpost_id")
    private BlogPost blogPost;

    // @ManyToOne
    // @JoinColumn(name = "blogpost_id", nullable = false)
    // private BlogPost blogPost;
}
