package com.github.dzeko14.SocialNetwork.service;

import com.github.dzeko14.SocialNetwork.exception.DatabaseNotRespondException;
import com.github.dzeko14.SocialNetwork.exception.InvalidEntityFieldException;
import com.github.dzeko14.SocialNetwork.exception.ResourceNotFoundException;
import com.github.dzeko14.SocialNetwork.model.Comment;
import com.github.dzeko14.SocialNetwork.model.Post;
import com.github.dzeko14.SocialNetwork.repository.CommentRepo;
import com.github.dzeko14.SocialNetwork.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class CommentService {
    private PostRepo postRepo;
    private CommentRepo commentRepository;

    @Autowired
    public CommentService(PostRepo repo, CommentRepo commentRepository) {
        this.postRepo = repo;
        this.commentRepository = commentRepository;
    }

    public Iterable<Comment> getCommentList() throws DatabaseNotRespondException {
        try {
            return filter(commentRepository.findAll());
        } catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }

    private Iterable<Comment> filter(Iterable<Comment> iterable) {
        Iterator<Comment> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Comment p = iterator.next();
            if (p.isDeleted()) iterator.remove();
        }
        return iterable;
    }

    public Comment getCommentById(Long id) throws ResourceNotFoundException {
        Comment comment =commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("There is no user with this id"));
        if (comment.isDeleted()) throw  new ResourceNotFoundException("There is no user with this id");
        return comment;
    }

    public Comment createComment(Comment comment) {

        if (comment.getComment() == null) throw new InvalidEntityFieldException("Field 'comment' is not correct!");
        if(comment.getPost() == null) throw new InvalidEntityFieldException("Field 'post' is not correct!");

        postRepo.findById(comment.getPost().getId())
                .orElseThrow(() -> new InvalidEntityFieldException("Field 'post' has not correct id!"));

        try {
            return commentRepository.save(comment);
        }  catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }

    }

    public Comment updateComment(long id, Comment updatedComment) {
        Comment oldComment = getCommentById(id);
        Comment newComment = Comment.merge(oldComment, updatedComment);
        try {
            return commentRepository.save(newComment);
        }  catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }

    public void deleteCommentById(long id) {
        try {
            Comment c = getCommentById(id);
            c.setDeleted(true);
            commentRepository.save(c);
        } catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }
}
