package ch.hftm.blogproject.util;

import java.util.List;
import java.util.stream.Collectors;

import ch.hftm.blogproject.model.dto.BlogPostDTO;
import ch.hftm.blogproject.model.dto.CommentDTO;
import ch.hftm.blogproject.model.entity.BlogPost;
import ch.hftm.blogproject.model.entity.Comment;

public class DTOConverter {
    
    public static List<BlogPostDTO> toBlogPostDtoList(List<BlogPost> blogPosts) {
        if (blogPosts == null || blogPosts.isEmpty()) {
            return null;
        }
        return blogPosts.stream().map(blogPost -> new BlogPostDTO() {
            {
            setBlogPostID(blogPost.getBlogPostID());
            setTitle(blogPost.getTitle());
            setContent(blogPost.getContent());
            setCreator(blogPost.getCreator());
            setCreatedAt(blogPost.getCreatedAt());
            setLastChangedAt(blogPost.getLastChangedAt());
            setComments(toCommentDtoList(blogPost.getComments()));
            }
        }).collect(Collectors.toList());
    }

    public static List<CommentDTO> toCommentDtoList(List<Comment> comments) {
        if (comments == null || comments.isEmpty()) {
            return null;
        }
        return comments.stream().map(comment -> new CommentDTO() {
            {
                setCommentID(comment.getCommentID());
                setBlogPostID(comment.getBlogPost().getBlogPostID());
                setContent(comment.getContent());
                setCreator(comment.getCreator());
                setCreatedAt(comment.getCreatedAt());
                setLastChangedAt(comment.getLastChangedAt());
            }
        }).collect(Collectors.toList());
    }

    public static List<Comment> toCommentList(List<CommentDTO> commentDTOs) {
        if (commentDTOs == null || commentDTOs.isEmpty()) {
            return null;
        }
        return commentDTOs.stream().map(commentDTO -> new Comment() {
            {
                setCommentID(commentDTO.getCommentID());
                setContent(commentDTO.getContent());
                setCreator(commentDTO.getCreator());
                setCreatedAt(commentDTO.getCreatedAt());
                setLastChangedAt(commentDTO.getLastChangedAt());
            }
        }).collect(Collectors.toList());
    }

    public static BlogPostDTO toBlogDto(BlogPost blog) {
        if (blog == null) {
            return null;
        }

        return new BlogPostDTO() {
            {
                setId(blog.getId());
                setTitle(blog.getTitle());
                setContent(blog.getContent());
                setAuthor(blog.getAuthor());
                setCreatedAt(blog.getCreatedAt());
                setLastEditedAt(blog.getLastEditedAt());
                setComments(toCommentDtoCollection(blog.getComments()));
            }
        };
    }
}
