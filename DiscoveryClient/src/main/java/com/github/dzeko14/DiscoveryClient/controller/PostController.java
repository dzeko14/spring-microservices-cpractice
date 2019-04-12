package com.github.dzeko14.DiscoveryClient.controller;

import com.github.dzeko14.DiscoveryClient.client.SocialNetworkClient;
import com.github.dzeko14.DiscoveryClient.model.Post;
import com.github.dzeko14.DiscoveryClient.exception.DatabaseNotRespondException;
import com.github.dzeko14.DiscoveryClient.exception.ResourceNotFoundException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private SocialNetworkClient socialNetworkClient;
    private SocialNetworkClient socialNetworkClient2;

    @Autowired
    public PostController(SocialNetworkClient pc1, SocialNetworkClient pc2) {
        socialNetworkClient = pc1;
        socialNetworkClient2 = pc2;
    }
    private boolean flag = false;
    @GetMapping
    public ResponseEntity<Object> getPosts() {
        try {
            flag = !flag;
            if (flag) {
                return ResponseEntity.ok(socialNetworkClient.getPosts());
            } else {
                return ResponseEntity.ok(socialNetworkClient2.getPosts());
            }
        } catch (FeignException e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(socialNetworkClient.getPost(id));
        } catch (FeignException e) {
            throw new ResourceNotFoundException("There is no user with this id");
        }
    }

    @PostMapping()
    public ResponseEntity<Object> savePost(@RequestBody Post post) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(socialNetworkClient.createPost(post));
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable long id,
                         @RequestBody Post post) {
        try {
            socialNetworkClient.updatePost(id, post);
        }  catch (FeignException e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) {
        try {
            socialNetworkClient.deletePost(id);
        } catch (FeignException e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }
}
