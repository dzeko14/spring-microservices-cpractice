package com.github.dzeko14.DiscoveryClient.model;

public class Post {
    private long id;
    private String content;
    private String title;

    private User author;

    public Post() {
    }

    public Post(long id, String content, String title, User author) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public static Post merge(Post oldOne, Post newOne){
        String title = newOne.title == null ? oldOne.title : newOne.title;
        String content = newOne.content == null ? oldOne.content : newOne.content;

        return new Post(oldOne.id, content, title, oldOne.author);
    }
}
