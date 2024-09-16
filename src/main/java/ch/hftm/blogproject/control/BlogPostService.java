package ch.hftm.blogproject.control;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import ch.hftm.blogproject.model.dto.BlogPostDTO;
import ch.hftm.blogproject.model.entity.BlogPost;
import ch.hftm.blogproject.model.exception.DatabaseException;
import ch.hftm.blogproject.model.exception.NotFoundException;
import ch.hftm.blogproject.repository.AccountRepository;
import ch.hftm.blogproject.repository.BlogPostRepository;
import ch.hftm.blogproject.util.DTOConverter;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class BlogPostService {

    @Inject
    BlogPostRepository blogPostRepository;
    @Inject
    AccountRepository accountRepository;

    // Get all BlogPosts with search and pagination
    public List<BlogPostDTO> getBlogPosts(Optional<String> searchString, Optional<Integer> page) {
        PanacheQuery<BlogPost> blogPostQuery;
        try {
            if (searchString == null || searchString.isEmpty()) {
                blogPostQuery = blogPostRepository.findAll();
            } else {
                blogPostQuery = blogPostRepository.find("title like ?1 or content like ?1", "%" + searchString.get() + "%");
            } 
            int pageNumber = page.orElse(0);
            List<BlogPost> blogposts = blogPostQuery.page(Page.of(pageNumber, 15)).list();
            return DTOConverter.toBlogPostDtoList(blogposts);
        } catch (Exception e) {
            throw new DatabaseException("Error while accessing the database.", e);
        }
    }

    // Get All Blogs with Sorting
    public List<BlogPostDTO> getAllBlogPostsSorted(String sortBy, boolean ascending) {
        try {
            Sort sort = ascending ? Sort.ascending(sortBy) : Sort.descending(sortBy);
            List<BlogPost> blogPosts = blogPostRepository.findAll(sort).list();
            return DTOConverter.toBlogPostDtoList(blogPosts);
        } catch (Exception e) {
            throw new DatabaseException("Error while accessing the database.", e);
        }
    }

    // Get a BlogPost by id
    public BlogPostDTO getBlogPostById(Long blogPostID) {
        BlogPost blogPost;
        try {
            blogPost = blogPostRepository.findById(blogPostID);
        } catch (Exception e) {
            throw new DatabaseException("Error while accessing the database.", e);
        }
        if (blogPost == null) {
            throw new NotFoundException("Blog post with ID " + blogPostID + " not found.");
        }
        return DTOConverter.toBlogPostDto(blogPost);
    }

    // Add a new BlogPost
    @Transactional
    public BlogPostDTO addBlogPost(BlogPostDTO blogPostDTO) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setContent(blogPostDTO.getContent());
        blogPost.setCreator(blogPostDTO.getCreator());
        blogPost.setCreatedAt(ZonedDateTime.now());
        try {
            blogPostRepository.persist(blogPost);
        } catch (Exception e) {
            throw new DatabaseException("Error while adding the blog post to the database.", e);
        }
        return DTOConverter.toBlogPostDto(blogPost);
    }

     // Update an existing BlogPost
     @Transactional
     public void updateBlogPost(Long id, BlogPostDTO blogPostDTO) {
         BlogPost existingBlogPost = blogPostRepository.findById(id);
         if (existingBlogPost != null) {
             // Only update the fields that are provided in the request
             if (blogPostDTO.getTitle() != null) {
                 existingBlogPost.setTitle(blogPostDTO.getTitle());
             }
             if (blogPostDTO.getContent() != null) {
                 existingBlogPost.setContent(blogPostDTO.getContent());
             }
             existingBlogPost.setLastChangedAt(ZonedDateTime.now());
         } else {
             throw new IllegalArgumentException("BlogPost not found for id: " + id);
         }
     }

    // Update blogPost
    @Transactional
    public BlogPostDTO updateBlogPost(BlogPostDTO blogPostDTO) {
        BlogPost blogPost;
        try {
            blogPost = blogPostRepository.findById(blogPostDTO.getBlogPostID());
        } catch (Exception e) {
            throw new DatabaseException("Error while accessing the database.", e);
        }
        if (blogPost == null) {
            throw new NotFoundException("Blog post with ID " + blogPostDTO.getBlogPostID() + " not found.");
        }

        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setContent(blogPostDTO.getContent());
        blogPost.setLastChangedAt(ZonedDateTime.now());

        try {
            blogPostRepository.persist(blogPost);
        } catch (Exception e) {
            throw new DatabaseException("Error while updating the blog post with ID " + blogPostDTO.getBlogPostID(), e);
        }
        return DTOConverter.toBlogPostDto(blogPost);
    }

    // Delete a BlogPost by id
    @Transactional
    public BlogPostDTO deleteBlogPost (Long blogPostID) {

        BlogPost blogPost = blogPostRepository.findById(blogPostID);
        if (blogPost == null) {
            throw new NotFoundException("Blog post with ID " + blogPostID + " not found.");
        }
        BlogPostDTO deletedBlogPostDTO = DTOConverter.toBlogPostDto(blogPost);
        try {
            blogPostRepository.deleteById(blogPostID);
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting the blog post with ID " + blogPostID, e);
        }
        return deletedBlogPostDTO;
    }

    // Delete all BlogPosts
    @Transactional
    public void deleteAllBlogPosts() {
        try {
            blogPostRepository.deleteAll(); // Delete all blog posts in the repository
        } catch (Exception e) {
            throw new DatabaseException("Error while deleting all blog posts.", e);
        }
    }
}
