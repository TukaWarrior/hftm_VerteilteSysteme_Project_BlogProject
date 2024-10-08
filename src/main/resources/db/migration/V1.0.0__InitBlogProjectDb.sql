CREATE TABLE BlogPost(
    blogPostID      BIGINT AUTO_INCREMENT NOT NULL,
    title           VARCHAR(255) NULL,
    content         TEXT NULL,
    creator         VARCHAR(255) NULL,
    createdAt       TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    lastChangedAt   TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_blogPost PRIMARY KEY (blogPostID)
);

CREATE TABLE Comment(
    commentID       BIGINT AUTO_INCREMENT NOT NULL,
    content         TEXT NULL,
    creator         VARCHAR(255) NULL,
    createdAt       TIMESTAMP  NULL DEFAULT CURRENT_TIMESTAMP,
    lastChangedAt   TIMESTAMP  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    blogPostID      BIGINT NULL,
    CONSTRAINT PK_comment PRIMARY KEY (commentID)
);

ALTER TABLE Comment
    ADD CONSTRAINT FK_COMMENT_ON_BLOG FOREIGN KEY (blogPostID) REFERENCES BlogPost (blogPostID) ON DELETE CASCADE;