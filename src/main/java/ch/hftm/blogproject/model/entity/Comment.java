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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private ZonedDateTime createdAt;
    private ZonedDateTime changedAt;

    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "blogpost_id", nullable = false)
    private BlogPost blogPost;
}
