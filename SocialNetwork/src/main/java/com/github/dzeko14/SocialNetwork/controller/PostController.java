package com.github.dzeko14.SocialNetwork.controller;

import com.github.dzeko14.SocialNetwork.exception.InvalidEntityFieldException;
import com.github.dzeko14.SocialNetwork.model.Post;
import com.github.dzeko14.SocialNetwork.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;


    @Value("${post:Default}")
    public String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Post>> getPosts() {
        return ResponseEntity.ok(postService.getPostList());
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable long id) {
        return postService.getPostById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> savePost(@RequestBody Post post) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(postService.createPost(post));
        } catch (InvalidEntityFieldException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable long id,
                         @RequestBody Post post) {
        postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePostById(id);
    }
}
