package ch.hftm.blogproject.control;

import ch.hftm.blogproject.entity.Blog;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;

// This class serves testing purposes. It adds blogs to the database upon each quarkus startup / refresh. 
// This makes testing Get requests easier. 

@ApplicationScoped
@Startup 
public class StartupBean {

    @Inject
    BlogService blogService;

    @PostConstruct
    public void init() {
        Log.info("Initializing database with sample blogs...");
        blogService.addBlog(new Blog(null, "Initial Blog 1", "Content of initial Blog One"));
        blogService.addBlog(new Blog(null, "Initial Blog 2", "Content of initial Blog Two"));
        blogService.addBlog(new Blog(null, "Initial Blog 3", "Content of initial Blog Three"));
        blogService.addBlog(new Blog(null, "Initial Blog 4", "Content of initial Blog Four"));
        blogService.addBlog(new Blog(null, "Initial Blog 5", "Content of initial Blog Five"));
        Log.info("Sample blogs added.");
    }
}
