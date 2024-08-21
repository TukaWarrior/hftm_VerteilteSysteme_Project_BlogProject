package ch.hftm.blogproject.boundary.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import ch.hftm.blogproject.entity.Comment;

public record NewCommentDTO(@NotNull @NotBlank String content) {
    public Comment toComment() {
        return new Comment (content);
    }
}
