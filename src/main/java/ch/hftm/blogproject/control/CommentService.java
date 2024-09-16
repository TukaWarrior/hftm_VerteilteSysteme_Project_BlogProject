package ch.hftm.blogproject.control;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import ch.hftm.blogproject.model.dto.CommentDTO;
import ch.hftm.blogproject.model.entity.BlogPost;
import ch.hftm.blogproject.model.entity.Comment;
import ch.hftm.blogproject.model.exception.DatabaseException;
import ch.hftm.blogproject.model.exception.NotFoundException;
import ch.hftm.blogproject.repository.BlogPostRepository;
import ch.hftm.blogproject.repository.CommentRepository;
import ch.hftm.blogproject.util.DTOConverter;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CommentService {

    @Inject
    CommentRepository commentRepository;

    @Inject
    BlogPostRepository blogPostRepository;

    // Get all comments with optional filtering
    public List<CommentDTO> getAllCommentsWithOptionalFiltering(Optional<String> searchString, Optional<Integer> page) {
        PanacheQuery<Comment> commentQuery;
        try {
            if (searchString.isEmpty() || searchString.get().isEmpty()) {
                commentQuery = commentRepository.findAll();
            } else {
                commentQuery = commentRepository.find("content like ?1", "%" + searchString.get() + "%");
            }
            int pageNumber = page.orElse(0);
            List<Comment> comments = commentQuery.page(Page.of(pageNumber, 20)).list();
            return DTOConverter.toCommentDtoList(comments);
        } catch (Exception e) {
            throw new DatabaseException("Error while getting comments.", e);
        }
    }

    // Get all comments for a specific blog post
    public List<CommentDTO> getAllCommentsFromBlog(Long blogId) {
        try {
            BlogPost blog = blogPostRepository.findById(blogId);
            if (blog == null) {
                throw new NotFoundException("Blog post with ID " + blogId + " not found.");
            }
            return DTOConverter.toCommentDtoList(blog.getComments());
        } catch (Exception e) {
            throw new DatabaseException("Error while getting comments from blog with ID " + blogId, e);
        }
    }

    // Get a single comment by ID
    public CommentDTO getCommentById(Long commentId) {
        try {
            Comment existingComment = commentRepository.findById(commentId);
            if (existingComment == null) {
                throw new NotFoundException("Comment with ID " + commentId + " not found.");
            }
            return DTOConverter.toCommentDto(existingComment);
        } catch (Exception e) {
            throw new DatabaseException("Error while getting comment with ID " + commentId, e);
        }
    }

    // Get a single comment from a specific blog post
    public CommentDTO getSingleCommentFromBlog(Long blogId, Long commentId) {
        try {
            BlogPost blog = blogPostRepository.findById(blogId);
            if (blog == null) {
                throw new NotFoundException("Blog post with ID " + blogId + " not found.");
            }

            Comment existingComment = commentRepository.findById(commentId);
            if (existingComment == null || !existingComment.getBlogPost().getBlogPostID().equals(blogId)) {
                throw new NotFoundException("Comment with ID " + commentId + " not found for blog post with ID " + blogId);
            }
            return DTOConverter.toCommentDto(existingComment);
        } catch (Exception e) {
            throw new DatabaseException("Error while getting comment with ID " + commentId + " from blog with ID " + blogId, e);
        }
    }

    // Add a new comment to a blog post
    @Transactional
    public CommentDTO addCommentToBlog(Long blogId, CommentDTO commentDTO) {
        try {
            BlogPost existingBlog = blogPostRepository.findById(blogId);
            if (existingBlog == null) {
                throw new NotFoundException("Blog post with ID " + blogId + " not found.");
            }

            // Calculate next comment number for the blog post
            Long nextCommentNumber = commentRepository.count("blog.id", blogId) + 1;

            Comment comment = new Comment();
            // comment.setCommentNumber(nextCommentNumber); // Comment number specific to this blog post
            comment.setContent(commentDTO.getContent());
            comment.setCreator(commentDTO.getCreator());
            comment.setCreatedAt(ZonedDateTime.now());
            comment.setBlogPost(existingBlog);

            commentRepository.persist(comment);
            return DTOConverter.toCommentDto(comment);
        } catch (Exception e) {
            throw new DatabaseException("Error while adding comment to blog post with ID " + blogId, e);
        }
    }

    // Update an existing comment for a specific blog post
    @Transactional
    public CommentDTO updateCommentOfBlog(Long blogId, CommentDTO commentDTO) {
        Long commentId = commentDTO.getCommentID();

        try {
            BlogPost existingBlog = blogPostRepository.findById(blogId);
            if (existingBlog == null) {
                throw new NotFoundException("Blog post with ID " + blogId + " not found.");
            }

            Comment existingComment = commentRepository.findById(commentId);
            if (existingComment == null || !existingComment.getBlogPost().getBlogPostID().equals(blogId)) {
                throw new NotFoundException("Comment with ID " + commentId + " not found for blog post with ID " + blogId);
            }

            existingComment.setContent(commentDTO.getContent());
            existingComment.setLastChangedAt(ZonedDateTime.now());
            commentRepository.persist(existingComment);

            return DTOConverter.toCommentDto(existingComment);
        } catch (Exception e) {
            throw new DatabaseException("Error while updating comment with ID " + commentId + " for blog post with ID " + blogId, e);
        }
    }

    // Delete a comment from a specific blog post
    @Transactional
    public CommentDTO deleteCommentFromBlog(Long blogId, Long commentId) {
        try {
            BlogPost existingBlog = blogPostRepository.findById(blogId);
            if (existingBlog == null) {
                throw new NotFoundException("Blog post with ID " + blogId + " not found.");
            }

            Comment existingComment = commentRepository.findById(commentId);
            if (existingComment == null || !existingComment.getBlogPost().getBlogPostID().equals(blogId)) {
                throw new NotFoundException("Comment with ID " + commentId + " not found for blog post with ID " + blogId);
            }

            commentRepository.delete(existingComment);
            return DTOConverter.toCommentDto(existingComment);
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting comment with ID " + commentId + " from blog post with ID " + blogId, e);
        }
    }
}
