package com.github.dzeko14.DiscoveryClient.model;

public class Comment {
    private long id;
    private String comment;

    private Post post;

    public Comment() {
    }

    public Comment(long id, String comment, Post post) {
        this.comment = comment;
        this.post = post;
        this.id = id;
    }

    public static Comment merge(Comment oldComment, Comment updatedComment) {
        String comment = updatedComment.comment == null ? oldComment.comment : updatedComment.comment;
        return new Comment(oldComment.id, comment, oldComment.post);
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
}
