// package ch.hftm.blogproject.control;

// import java.time.ZonedDateTime;
// import java.util.List;
// import java.util.Optional;

// import ch.hftm.blogproject.model.dto.BlogPostDTO;
// import ch.hftm.blogproject.model.entity.BlogPost;
// import ch.hftm.blogproject.repository.AccountRepository;
// import ch.hftm.blogproject.repository.BlogPostRepository;
// import io.quarkus.panache.common.Page;
// import io.quarkus.panache.common.Sort;
// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.inject.Inject;
// import jakarta.transaction.Transactional;
// import jakarta.ws.rs.core.Response;

// @ApplicationScoped
// public class BlogPostService {

//     @Inject
//     BlogPostRepository blogPostRepository;

//     @Inject
//     AccountRepository accountRepository;

//     private static final int PAGE_SIZE = 3; // Define the page size for pagination

//     // // Get all BlogPosts
//     // public List<BlogPostDTO> getAllBlogPosts() {
//     //     return blogPostRepository.listAll().stream()
//     //             .map(BlogPostDTO::new)
//     //             .toList();
//     // }

//     // Get all BlogPosts with search and pagination
//     public List<BlogPostDTO> getBlogPosts(String searchString, Integer page) {
//         if (searchString == null || searchString.isBlank()) {
//             // If no search string is provided, return all blogs with pagination
//             return blogPostRepository.findAll(Sort.by("createdAt").descending())
//                     .page(Page.of(page - 1, PAGE_SIZE))
//                     .stream()
//                     .map(BlogPostDTO::new)
//                     .toList();
//         } else {
//             // Search by title with pagination
//             return blogPostRepository.find("title like ?1", Sort.by("createdAt").descending(), "%" + searchString + "%")
//                     .page(Page.of(page - 1, PAGE_SIZE))
//                     .stream()
//                     .map(BlogPostDTO::new)
//                     .toList();
//         }
//     }

//     // Get a BlogPost by id
//     public Optional<BlogPostDTO> getBlogPostById(Long id) {
//         return Optional.ofNullable(blogPostRepository.findById(id))
//                        .map(BlogPostDTO::new);
//     }

//     // Add a new BlogPost
//     @Transactional
//     public BlogPostDTO addBlogPost(BlogPostDTO blogPostDTO, Long accountId) {
//         if (accountRepository.findById(accountId) == null) {
//             throw new IllegalArgumentException("Invalid account ID: " + accountId);
//         }
//         BlogPost blogPost = blogPostDTO.toEntity();
//         blogPost.setCreatedAt(ZonedDateTime.now());
//         blogPost.setLastChangedAt(ZonedDateTime.now());
//         blogPostRepository.persist(blogPost);
//         return new BlogPostDTO(blogPost);
//     }

//      // Update an existing BlogPost
//      @Transactional
//      public void updateBlogPost(Long id, BlogPostDTO blogPostDTO) {
//          BlogPost existingBlogPost = blogPostRepository.findById(id);
//          if (existingBlogPost != null) {
//              // Only update the fields that are provided in the request
//              if (blogPostDTO.getTitle() != null) {
//                  existingBlogPost.setTitle(blogPostDTO.getTitle());
//              }
//              if (blogPostDTO.getContent() != null) {
//                  existingBlogPost.setContent(blogPostDTO.getContent());
//              }
//              existingBlogPost.setLastChangedAt(ZonedDateTime.now());
//          } else {
//              throw new IllegalArgumentException("BlogPost not found for id: " + id);
//          }
//      }

//     // Delete a BlogPost by id
//     @Transactional
//     public void deleteBlogPost(Long id) {
//         BlogPost blogPost = blogPostRepository.findById(id);
//         if (blogPost != null) {
//             blogPostRepository.delete(blogPost);
//         } else {
//             throw new IllegalArgumentException("Blog post with id " + id + " not found.");
//         }
//     }
// }
