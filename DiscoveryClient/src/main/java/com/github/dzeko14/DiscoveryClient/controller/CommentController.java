package com.github.dzeko14.DiscoveryClient.controller;

import com.github.dzeko14.DiscoveryClient.client.SocialNetworkClient;
import com.github.dzeko14.DiscoveryClient.exception.DatabaseNotRespondException;
import com.github.dzeko14.DiscoveryClient.exception.ResourceNotFoundException;
import com.github.dzeko14.DiscoveryClient.model.Comment;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private SocialNetworkClient socialNetworkClient;
   // private SocialNetworkClient socialNetworkClient2;

    @Autowired
    public CommentController(SocialNetworkClient cc1) {
        this.socialNetworkClient = cc1;
        ///socialNetworkClient2 = cc2;
    }

    @GetMapping
    public ResponseEntity<Object> getComment() {
        try {
//            flag = !flag;
//            if (flag) {
                return ResponseEntity.ok(socialNetworkClient.getComments());
//            } else {
//                return ResponseEntity.ok(socialNetworkClient2.getComments());
//            }
        } catch (FeignException e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCommentById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(socialNetworkClient.getComment(id));
        } catch (FeignException e) {
            throw new ResourceNotFoundException("There is no user with this id");
        }
    }

    @PostMapping()
    public ResponseEntity<Object> saveComment(@RequestBody Comment comment) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(socialNetworkClient.createComment(comment));
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void updateComment(@PathVariable long id,
                           @RequestBody Comment comment) {
        try {
            socialNetworkClient.updatePost(id, comment);
        }  catch (FeignException e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        try {
            socialNetworkClient.deleteComment(id);
        } catch (FeignException e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }
}
