package ch.hftm.blogproject.boundary.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import ch.hftm.blogproject.entity.Comment;

public record NewCommentDTO(@NotNull @NotBlank @Length(min = 5) String content) {
    public Comment toComment() {
        return new Comment (content);
    }
}
