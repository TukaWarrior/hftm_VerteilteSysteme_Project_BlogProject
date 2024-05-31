package ch.hftm.blogproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Comment {
    @Id
    @GeneratedValue
 
    String comment = "";
}
