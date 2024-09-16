package ch.hftm.blogproject.util;

import java.util.List;
import java.util.stream.Collectors;

import ch.hftm.blogproject.model.dto.BlogPostDTO;
import ch.hftm.blogproject.model.entity.BlogPost;

public class DTOConverter {
    
    public static List<BlogPostDTO> toBlogPostDtoList(List<BlogPost> blogPosts) {
        if (blogPosts == null || blogPosts.isEmpty()) {
            return null;
        }
        return blogPosts.stream().map(blogPost -> new BlogPostDTO() {
            {
            setBlogPostID(getBlogPostID());(blogPost.getBlogPostID());
            setTitle(blogPost.getTitle());
            setContent(blogPost.getContent());
            setCreator(blogPost.getCreator());
            setCreatedAt(blogPost.getCreatedAt());
            setLastChangedAt(blogPost.getLastChangedAt());
            setComments(toCommentDtoCollection(blogPost.getComments()));
            }
        }).collect(Collectors.toList());
    }
}
