package ch.hftm.blogproject.boundary.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;
import ch.hftm.blogproject.entity.Blog;

public record NewBlogDTO(@NotNull @Length(min = 5) String title, String content) {
    public Blog toBlog() {
        return new Blog(title, content);
    }
}
