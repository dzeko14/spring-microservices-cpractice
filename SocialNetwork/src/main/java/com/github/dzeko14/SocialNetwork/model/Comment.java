package com.github.dzeko14.SocialNetwork.model;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String comment;

    private boolean isDeleted;

    @ManyToOne(targetEntity = Post.class, cascade = CascadeType.REMOVE)
    @JoinColumn
    private Post post;

    public Comment() {
    }

    public Comment(long id, String comment,  Post post, boolean isDeleted) {
        this.comment = comment;
        this.isDeleted = isDeleted;
        this.post = post;
        this.id = id;
    }

    public static Comment merge(Comment oldComment, Comment updatedComment) {
        String comment = updatedComment.comment == null ? oldComment.comment : updatedComment.comment;
        return new Comment(oldComment.id, comment, oldComment.post, oldComment.isDeleted);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
