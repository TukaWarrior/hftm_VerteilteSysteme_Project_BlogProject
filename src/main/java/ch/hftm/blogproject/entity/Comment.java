// This class is currently unused. It serves as a WIP idea t oimplement comments under a blog post.
package ch.hftm.blogproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Comment {
    @Id
    @GeneratedValue
 
    String comment = "";
}
