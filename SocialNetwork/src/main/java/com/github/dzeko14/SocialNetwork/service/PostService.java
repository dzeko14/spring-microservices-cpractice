package com.github.dzeko14.SocialNetwork.service;

import com.github.dzeko14.SocialNetwork.exception.DatabaseNotRespondException;
import com.github.dzeko14.SocialNetwork.exception.InvalidEntityFieldException;
import com.github.dzeko14.SocialNetwork.exception.ResourceNotFoundException;
import com.github.dzeko14.SocialNetwork.model.Comment;
import com.github.dzeko14.SocialNetwork.model.Post;
import com.github.dzeko14.SocialNetwork.model.User;
import com.github.dzeko14.SocialNetwork.repository.PostRepo;
import com.github.dzeko14.SocialNetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class PostService {
    private PostRepo postRepo;
    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepo repo, UserRepository userRepository) {
        this.postRepo = repo;
        this.userRepository = userRepository;
    }

    public Iterable<Post> getPostList() throws DatabaseNotRespondException {
        try {
            return  filter(postRepo.findAll());
        } catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }

    private Iterable<Post> filter(Iterable<Post> iterable) {
        Iterator<Post> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Post p = iterator.next();
            if (p.isDeleted()) iterator.remove();
        }
        return iterable;
    }

    public Post getPostById(Long id) throws ResourceNotFoundException {
        Post p = postRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("There is no user with this id"));
        if (p.isDeleted()) throw new ResourceNotFoundException("There is no user with this id");
        return p;
    }

    public Post createPost(Post post) {

        if (post.getTitle() == null) throw new InvalidEntityFieldException("Field 'title' is not correct!");
        if(post.getContent() == null) throw new InvalidEntityFieldException("Field 'content' is not correct!");
        if( post.getAuthor() == null) throw new InvalidEntityFieldException("Field 'author' is not correct!");

        User user = userRepository
                .findById(post.getAuthor().getId())
                .orElseThrow(() -> new InvalidEntityFieldException("Field 'author' has not correct id!"));

        try {
            return postRepo.save(post);
        }  catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }

    }

    public Post updatePost(long id, Post updatedPost) {
        Post oldPost = getPostById(id);

        Post newPost = Post.merge(oldPost, updatedPost);
        try {
            return postRepo.save(newPost);
        }  catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }



    public void deletePostById(long id) {
        try {
            Post c = getPostById(id);
            c.setDeleted(true);
            postRepo.save(c);
        } catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }
}
