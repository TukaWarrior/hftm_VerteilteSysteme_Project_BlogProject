package ch.hftm.blogproject.control;

import java.time.ZonedDateTime;
import java.util.List;

import ch.hftm.blogproject.boundary.dto.BlogPostDTO;
import ch.hftm.blogproject.entity.Account;
import ch.hftm.blogproject.entity.BlogPost;
import ch.hftm.blogproject.repository.AccountRepository;
import ch.hftm.blogproject.repository.BlogPostRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


// This class handles the business logic for the blog posts. It interacts with the BlogRessource class and the BlogRepository class.

@ApplicationScoped
public class BlogPostService {

    int pageSize = 4; // Settings: Defines the numbers of entries returned per page.

    @Inject
    private BlogPostRepository blogPostRepository;
    @Inject
    private AccountRepository accountRepository;

    // Returns one list with all BlogPosts.
    public List<BlogPost> getAllBlogPosts(){
        return blogPostRepository.listAll();
    }

    // Returns one BlogPost matching the passed id.
    public BlogPost getBlogPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    // Adds one BlogPost.
    @Transactional
    public BlogPost addBlogPost(BlogPostDTO blogPostDTO, Long accountId){
        // Account account = accountRepository.findById(accountId);
        if (accountId != null) {
            BlogPost blogPost = blogPostDTO.toEntity(accountId);
            blogPostRepository.persist(blogPost);
            return blogPost;
        } else {
            throw new IllegalArgumentException("Invalid account id: " + blogPostDTO.getAccountId());
        }
    }

    // Updates one BlogPost.
    @Transactional
    public void updateBlogPost(Long id, BlogPostDTO  blogPostDTO){
        BlogPost existingBlogPost = blogPostRepository.findById(id);
        if (existingBlogPost != null) {
            existingBlogPost.setTitle(blogPostDTO.getTitle());
            existingBlogPost.setContent(blogPostDTO.getContent());
            existingBlogPost.setChangedAt(ZonedDateTime.now());
        }
    }

    // Deletes one BlogPost by id.
    @Transactional
    public void deleteBlogPost(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id);
        if (blogPost == null) {
            throw new IllegalArgumentException("Blog post with id " + id + " not found.");
        }
        blogPostRepository.delete(blogPost);
    }

}

    // // Returns a list of blogs that match the passed search terms. 
    // public List<BlogPost> getBlogs(Optional<String> searchString, Optional<Long> pageNumber) {
    //     PanacheQuery<BlogPost> blogQuery;
    //     if (searchString.isEmpty()) {
    //         blogQuery = blogRepository.findAll();
    //     } else {
    //         blogQuery = blogRepository.find("title like ?1 or content like ?1", "%" + searchString.get() +"%");
    //     }
    
    //     long pageIndex = pageNumber.orElse(1L);
    //     // List<Blog> blogs = blogQuery.page(Page.ofSize(2)).list(); // Old code. Just define the page size. For automatic scrolling in frontend? 
    //     List<BlogPost> blogs = blogQuery.page(Page.of((int) (pageIndex - 1), pageSize)).list();
    //     Log.info("Returning " + blogs.size() + " blogs");
    //     return blogs;
    // }

    // // Creates a new blog with a new id.
    // @Transactional
    // public BlogPost addBlog(BlogPost blog) {
    //     Log.info("Adding blog " + blog.getTitle());
    //     blogRepository.persist(blog);
    //     return blog;
    // }

    // // Deletes an existing blog with a matching id.
    // @Transactional
    // public BlogPost deleteBlog(long id) {
    //     BlogPost blog = blogRepository.findById(id);
    //     if (blog != null) {
    //         blogRepository.delete(blog);
    //         Log.info("Deleted blog with id " + id);
    //         return blog;
    //     } else {
    //         Log.warn("Blog with id " + id + " not found for deletion");
    //         return null;
    //     }
    // }

    // // Replaces attributes of an existing blog with a matching id.
    // @Transactional
    // public BlogPost putBlog(Long id, BlogPost newBlog) {
    //     BlogPost existingBlog = blogRepository.findById(id);
    //     if (existingBlog != null) {
    //         // I treid to replace the existing blog entity completely but it would just create a new blog. SOme problems with the Primary key. 
    //         // For now, I just replace each attribute manualy, which seems to be suboptimal.
    //         existingBlog.setTitle(newBlog.getTitle());
    //         existingBlog.setContent(newBlog.getContent());
    //         existingBlog.setChangedAt(ZonedDateTime.now());
    //         blogRepository.persist(existingBlog);
    //         Log.info("Replaced blog with id " + id);
    //         return existingBlog;
    //     } else {
    //         Log.warn("Blog with id " + id + " not found for replacement");
    //         return null;
    //     }
    // }

    // // Replaces attributes of existing blog with a matching id only when they are not empty.
    // @Transactional
    // public BlogPost patchBlog(long id, BlogPost newBlog) {
    //     BlogPost existingBlog = blogRepository.findById(id);
    //     if (existingBlog != null) {
    //         if (newBlog.getTitle().isEmpty() != true) {
    //             existingBlog.setTitle(newBlog.getTitle());
    //         }
    //         if (newBlog.getContent().isEmpty() != true) {
    //             existingBlog.setContent(newBlog.getContent());
    //         }
    //         existingBlog.setChangedAt(ZonedDateTime.now());
    //         blogRepository.persist(existingBlog);
    //         Log.info("Partially updated blog with id " + id);
    //         return existingBlog;
    //     } else {
    //         Log.warn("Blog with id " + id + " not found for partial update");
    //     }
    //     return null;
    // }
