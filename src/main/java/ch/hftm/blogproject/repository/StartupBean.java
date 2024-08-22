package ch.hftm.blogproject.repository;

import ch.hftm.blogproject.boundary.dto.BlogPostDTO;
import ch.hftm.blogproject.boundary.dto.CommentDTO;
import ch.hftm.blogproject.control.AccountService;
import ch.hftm.blogproject.control.BlogPostService;
import ch.hftm.blogproject.control.CommentService;
import ch.hftm.blogproject.entity.Account;
import ch.hftm.blogproject.entity.BlogPost;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.logging.Log;
import java.time.ZonedDateTime;
import io.quarkus.runtime.Startup;

// This class serves testing purposes. It adds entities to the database upon quarkus startup / refresh. 
// This makes testing GET requests easier. 

@ApplicationScoped
@Startup 
public class StartupBean {

    @Inject
    BlogPostService blogPostService;
    @Inject
    AccountService accountService;
    @Inject
    CommentService commentService;

    @PostConstruct
    public void init() {
        Log.info("Initializing database with example Accoutns, BlogPosts, Comments...");

        Log.info("Initializing database with example Accoutns...");
        accountService.addAccount(new Account(null, "Alice", "alice@email.com", "admin", ZonedDateTime.now(), null, null));
        accountService.addAccount(new Account(null, "Brian", "brian@email.com", "moderator", ZonedDateTime.now(), null, null));
        accountService.addAccount(new Account(null, "Chloe", "chloe@email.com", "user", ZonedDateTime.now(), null, null));
        accountService.addAccount(new Account(null, "Dave", "dave@email.com", "user", ZonedDateTime.now(), null, null));
        accountService.addAccount(new Account(null, "Eli", "eli@email.com", "user", ZonedDateTime.now(), null, null));

        Log.info("Initializing database with example BlogPosts...");
        blogPostService.addBlogPost(new BlogPostDTO("Initial BlogPost 1", "Content of initial BlogPost 1"), 1L);
        blogPostService.addBlogPost(new BlogPostDTO("Initial BlogPost 2", "Content of initial BlogPost 2"), 2L);
        blogPostService.addBlogPost(new BlogPostDTO("Initial BlogPost 3", "Content of initial BlogPost 3"), 3L);
        blogPostService.addBlogPost(new BlogPostDTO("Initial BlogPost 4", "Content of initial BlogPost 4"), 4L);
        blogPostService.addBlogPost(new BlogPostDTO("Initial BlogPost 5", "Content of initial BlogPost 5"), 5L);

        Log.info("Initializing database with example Comments...");
        commentService.addComment(new CommentDTO("Comment 1 on BlogPost 1"), 1L, 1L);
        commentService.addComment(new CommentDTO("Comment 2 on BlogPost 1"), 1L, 2L);
        commentService.addComment(new CommentDTO("Comment 3 on BlogPost 1"), 1L, 3L);
        commentService.addComment(new CommentDTO("Comment 4 on BlogPost 1"), 1L, 4L);
        commentService.addComment(new CommentDTO("Comment 5 on BlogPost 1"), 1L, 5L);

        commentService.addComment(new CommentDTO("Comment 1 on BlogPost 2"), 2L, 1L);
        commentService.addComment(new CommentDTO("Comment 2 on BlogPost 2"), 2L, 2L);
        commentService.addComment(new CommentDTO("Comment 3 on BlogPost 2"), 2L, 3L);
        commentService.addComment(new CommentDTO("Comment 4 on BlogPost 2"), 2L, 4L);
        commentService.addComment(new CommentDTO("Comment 5 on BlogPost 2"), 2L, 5L);

        commentService.addComment(new CommentDTO("Comment 1 on BlogPost 3"), 3L, 1L);
        commentService.addComment(new CommentDTO("Comment 2 on BlogPost 3"), 3L, 2L);
        commentService.addComment(new CommentDTO("Comment 3 on BlogPost 3"), 3L, 3L);
        commentService.addComment(new CommentDTO("Comment 4 on BlogPost 3"), 3L, 4L);
        commentService.addComment(new CommentDTO("Comment 5 on BlogPost 3"), 3L, 5L);

        commentService.addComment(new CommentDTO("Comment 1 on BlogPost 4"), 4L, 1L);
        commentService.addComment(new CommentDTO("Comment 2 on BlogPost 4"), 4L, 2L);
        commentService.addComment(new CommentDTO("Comment 3 on BlogPost 4"), 4L, 3L);
        commentService.addComment(new CommentDTO("Comment 4 on BlogPost 4"), 4L, 4L);
        commentService.addComment(new CommentDTO("Comment 5 on BlogPost 4"), 4L, 5L);

        commentService.addComment(new CommentDTO("Comment 1 on BlogPost 5"), 5L, 1L);
        commentService.addComment(new CommentDTO("Comment 2 on BlogPost 5"), 5L, 2L);
        commentService.addComment(new CommentDTO("Comment 3 on BlogPost 5"), 5L, 3L);
        commentService.addComment(new CommentDTO("Comment 4 on BlogPost 5"), 5L, 4L);
        commentService.addComment(new CommentDTO("Comment 5 on BlogPost 5"), 5L, 5L);
        Log.info("Example Accoutns, BlogPosts, Comments created.");
    }
}