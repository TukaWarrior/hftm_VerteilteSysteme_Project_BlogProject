package ch.hftm.blogproject.repository;

import ch.hftm.blogproject.control.AccountService;
import ch.hftm.blogproject.control.BlogService;
import ch.hftm.blogproject.entity.Account;
import ch.hftm.blogproject.entity.Blog;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;

// This class serves testing purposes. It adds blogs entities to the database upon quarkus startup / refresh. 
// This makes testing GET requests easier. 

@ApplicationScoped
@Startup 
public class StartupBean {

    @Inject
    BlogService blogService;
    @Inject
    AccountService accountService;

    @PostConstruct
    public void init() {
        Log.info("Initializing database with sample blogs...");
        blogService.addBlog(new Blog("Initial Blog 1", "Content of initial Blog One"));
        blogService.addBlog(new Blog("Initial Blog 2", "Content of initial Blog Two"));
        blogService.addBlog(new Blog("Initial Blog 3", "Content of initial Blog Three"));
        blogService.addBlog(new Blog("Initial Blog 4", "Content of initial Blog Four"));
        blogService.addBlog(new Blog("Initial Blog 5", "Content of initial Blog Five"));
        accountService.addAccount(new Account("Alex", "alex@email.com", "admin"));
        accountService.addAccount(new Account("Beatrice", "beatrice@email.com", "user"));
        accountService.addAccount(new Account("Carl", "carl@email.com", "user"));
        Log.info("Sample blogs added.");
    }
}
