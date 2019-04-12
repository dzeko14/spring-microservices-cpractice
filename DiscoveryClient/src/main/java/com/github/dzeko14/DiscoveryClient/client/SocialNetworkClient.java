package com.github.dzeko14.DiscoveryClient.client;

import com.github.dzeko14.DiscoveryClient.model.Comment;
import com.github.dzeko14.DiscoveryClient.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("social-network")
public interface SocialNetworkClient {
    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<Post> getPosts();

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    Post getPost(@PathVariable("id") long id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/posts/{id}")
    void deletePost(@PathVariable("id") long id);

    @RequestMapping(method = RequestMethod.POST, value = "/posts", consumes = "application/json")
    Post createPost(Post post);

    @RequestMapping(method = RequestMethod.PUT, value = "/posts/{id}", consumes = "application/json")
    void updatePost(@PathVariable("id") long id, Post post);

    @RequestMapping(method = RequestMethod.GET, value = "/comments")
    List<Comment> getComments();

    @RequestMapping(method = RequestMethod.GET, value = "/comments/{id}")
    Comment getComment(@PathVariable("id") long id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/comments/{id}")
    void deleteComment(@PathVariable("id") long id);

    @RequestMapping(method = RequestMethod.POST, value = "/comments", consumes = "application/json")
    Comment createComment(Comment post);

    @RequestMapping(method = RequestMethod.PUT, value = "/comments/{id}", consumes = "application/json")
    void updatePost(@PathVariable("id") long id, Comment post);
}
