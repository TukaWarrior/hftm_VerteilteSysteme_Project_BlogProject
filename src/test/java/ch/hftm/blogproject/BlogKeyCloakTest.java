// package org.acme;

// import static io.restassured.RestAssured.given;

// import static org.hamcrest.Matchers.hasSize;

// import org.junit.jupiter.api.Test;

// import ch.hftm.blogproject.control.AccountService;
// import ch.hftm.blogproject.entity.Account;

// import ch.hftm.blogproject.control.BlogPostService;
// import ch.hftm.blogproject.control.AccountService;
// import ch.hftm.blogproject.entity.BlogPost;
// import ch.hftm.blogproject.entity.Account;
// import io.quarkus.test.junit.QuarkusTest;
// import io.quarkus.test.keycloak.client.KeycloakTestClient;
// import jakarta.inject.Inject;

// @QuarkusTest
// public class BlogKeyCloakTest {

//     @Inject
//     BlogPostService blogPostService;

//     @Inject
//     AccountService accountService;

//     KeycloakTestClient keycloakTestClient = new KeycloakTestClient();

//     @Test
//     public void getAllBlogs() {
//         /*
//          * Checking all blogs, there is no restriction in the Blog Resource for getting
//          * all blogs
//          * all can check the available blogs
//          */
//         given()
//                 .when().get("/blogpost")
//                 .then()
//                 .statusCode(200);
//     }

//     @Test
//     public void getBlogID() {
//         /*
//          * Checking a blog with id 1, no restrictions as well in the Blog Resource for
//          * getting the blogs with
//          * a provided id
//          */
//         given()
//                 .when().get("/blogpost/1")
//                 .then()
//                 .statusCode(200);
//     }

//     // @Test
//     // public void getComment() {
//     //     /*
//     //      * Ok so I added two comments to blog with ID1, one from Fred and one from Ted
//     //      * I want to verify if the system sees them
//     //      */
//     //     given()
//     //             .when().get("comment/")
//     //             .then()
//     //             .statusCode(200)
//     //             .body("$", hasSize(2));
//     // }

//     // @Test
//     // public void addBlogTest() {
//     //     /*
//     //      * Monika, my wife, I assigned her the role of an author.
//     //      * Then we have Bob, he's a user, so he shouldn't be able to add blogs
//     //      */

//     //     // I begin with creating an author, Monika
//     //     Account account = new Account();
//     //     account.setName("Luca");
//     //     account.setEmail("luca@email.com");
//     //     accountService.addAuthor(account);
//     //     Long authorId = account.getId();

//     //     // Then I proceed with creating a blog
//     //     Blog blog = new Blog();
//     //     blog.setTitle("Blog to be added");
//     //     blog.setContent("This is a blog that is supposed to be added");

//     //     /*
//     //      * I will not lie, this was the most difficult method and I spend the most
//     //      * time on this one...
//     //      * I will also admit that I used ChatGPT for helping me with this but I checked
//     //      * what is what
//     //      */
//     //     // CHeck for Monika, expected is status code 201 -> created
//     //     given()
//     //             .auth().oauth2(keycloakTestClient.getAccessToken("monika"))
//     //             .header("authorId", authorId) // header is authorID as assigned in BlogResource
//     //             .contentType("application/json") // I inform the server that the data is in json format
//     //             .body("{\"title\":\"" + blog.getTitle() + "\", \"content\":\"" +
//     //                     blog.getContent() + "\"}") // this describes the body of the http request
//     //             .when().post("/blogs")
//     //             .then()
//     //             .statusCode(201);
//     //     // Check for Bob, I expect a status code 403 -> forbidden
//     //     given()
//     //             .auth().oauth2(keycloakTestClient.getAccessToken("bob"))
//     //             .header("authorId", authorId) //
//     //             .contentType("application/json")
//     //             .body("{\"title\":\"" + blog.getTitle() + "\", \"content\":\"" +
//     //                     blog.getContent() + "\"}")
//     //             .when().post("/blogs")
//     //             .then()
//     //             .statusCode(403);
//     // }

//     // @Test
//     // public void deleteBlog() {
//     //     /*
//     //      * Alice, given she is the admin, she should be only
//     //      * possible to delete the blog -> status code 204
//     //      * On the other side we have Tom. Tom is just a user
//     //      * therefore he shouldn't be allowed to delete the blog
//     //      */

//     //     // First I create the author
//     //     Account account = new Account();
//     //     account.setName("AuthorName");
//     //     account.setEmail("authorname@mail.com");
//     //     accountService.addAuthor(account);
//     //     Long authorId = account.getId();

//     //     // Then create the blog
//     //     Blog blog = new Blog();
//     //     blog.setTitle("Blog to be deleted");
//     //     blog.setContent("This is a blog that is supposed to be deleted");

//     //     // Add the blog to the blog service
//     //     blogPostService.addBlog(blog, authorId);

//     //     // here I have the check for Alice
//     //     given()
//     //             .auth().oauth2(keycloakTestClient.getAccessToken("alice"))
//     //             .when().delete("/blogs/" + blog.getId())
//     //             .then()
//     //             .statusCode(204);
//     //     // and here is the check for Tommy
//     //     given()
//     //             .auth().oauth2(keycloakTestClient.getAccessToken("tom"))
//     //             .when().delete("/blogs/" + blog.getId())
//     //             .then()
//     //             .statusCode(403);
//     // }
// }
