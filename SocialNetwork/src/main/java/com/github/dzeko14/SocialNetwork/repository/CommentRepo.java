package com.github.dzeko14.SocialNetwork.repository;

import com.github.dzeko14.SocialNetwork.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
}
