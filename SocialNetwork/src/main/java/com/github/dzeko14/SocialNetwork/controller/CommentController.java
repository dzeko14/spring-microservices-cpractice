package com.github.dzeko14.SocialNetwork.controller;

import com.github.dzeko14.SocialNetwork.exception.InvalidEntityFieldException;
import com.github.dzeko14.SocialNetwork.model.Comment;
import com.github.dzeko14.SocialNetwork.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;
    private Logger l = LoggerFactory.getLogger(Comment.class);


    @Value("${comment:Default}")
    public String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Comment>> getComment() {
        return ResponseEntity.ok(commentService.getCommentList());
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable long id) {
        l.info("getCommentById() " + hashCode());
        return commentService.getCommentById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> saveComment(@RequestBody Comment post) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(commentService.createComment(post));
        } catch (InvalidEntityFieldException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void updateComment(@PathVariable long id,
                           @RequestBody Comment post) {
        commentService.updateComment(id, post);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        commentService.deleteCommentById(id);
    }
}
