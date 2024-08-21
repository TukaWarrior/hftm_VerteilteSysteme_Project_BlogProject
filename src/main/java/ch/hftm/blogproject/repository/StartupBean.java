package ch.hftm.blogproject.repository;

import ch.hftm.blogproject.control.AccountService;
import ch.hftm.blogproject.control.BlogPostService;
import ch.hftm.blogproject.control.CommentService;
import ch.hftm.blogproject.entity.Account;
import ch.hftm.blogproject.entity.Blog;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.logging.Log;
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
        accountService.addAccount(new Account("Alice", "alice@email.com", "admin"));
        accountService.addAccount(new Account("Brian", "brian@email.com", "moderator"));
        accountService.addAccount(new Account("Chloe", "chloe@email.com", "user"));
        accountService.addAccount(new Account("Dave", "dave@email.com", "user"));
        accountService.addAccount(new Account("Eli", "eli@email.com", "user"));
        blogPostService.addBlogPost(new Blog("Initial Blog 1", "Content of initial Blog One"), 1L);
        blogPostService.addBlogPost(new Blog("Initial Blog 2", "Content of initial Blog Two"), 2L);
        blogPostService.addBlogPost(new Blog("Initial Blog 3", "Content of initial Blog Three"), 3L);
        blogPostService.addBlogPost(new Blog("Initial Blog 4", "Content of initial Blog Four"), 4L);
        blogPostService.addBlogPost(new Blog("Initial Blog 5", "Content of initial Blog Five"), 5L);
        commentService.addComment("Comment one on BlogPost One", 1L, 1L);
        commentService.addComment("Comment two on BlogPost One", 2L, 1L);
        commentService.addComment("Comment three on BlogPost One", 3L, 1L);
        commentService.addComment("Comment one on BlogPost Two", 1L, 2L);
        commentService.addComment("Comment two on BlogPost Two", 2L, 2L);
        commentService.addComment("Comment three on BlogPost Two", 3L, 2L);
        commentService.addComment("Comment one on BlogPost Three", 1L, 3L);
        commentService.addComment("Comment two on BlogPost Three", 2L, 3L);
        commentService.addComment("Comment three on BlogPost Three", 3L, 3L);
        commentService.addComment("Comment one on BlogPost Four", 1L, 4L);
        commentService.addComment("Comment two on BlogPost Four", 2L, 4L);
        commentService.addComment("Comment three on BlogPost Four", 3L, 4L);
        commentService.addComment("Comment one on BlogPost Five", 1L, 5L);
        commentService.addComment("Comment two on BlogPost Five", 2L, 5L);
        commentService.addComment("Comment three on BlogPost Five", 3L, 5L);
        Log.info("Example Accoutns, BlogPosts, Comments created.");
    }
}