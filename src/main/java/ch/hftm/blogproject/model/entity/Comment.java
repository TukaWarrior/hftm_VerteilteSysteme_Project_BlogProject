package ch.hftm.blogproject.model.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "comment_id")
    private Long commentID;
    // @Column(nullable = false)
    private String content;
    private String creator;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastChangedAt;

    @ManyToOne
    @JoinColumn(name = "blogpost_id")
    private BlogPost blogPost;
}
