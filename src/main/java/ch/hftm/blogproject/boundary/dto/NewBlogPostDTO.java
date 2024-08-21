package ch.hftm.blogproject.boundary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import ch.hftm.blogproject.entity.Blog;

public record NewBlogPostDTO(@NotNull @NotBlank @Length(min = 5) String title, String content) {
    public Blog toBlog() {
        return new Blog(title, content);
    }
}
