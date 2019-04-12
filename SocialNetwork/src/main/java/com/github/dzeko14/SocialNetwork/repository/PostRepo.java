package com.github.dzeko14.SocialNetwork.repository;

import com.github.dzeko14.SocialNetwork.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {
}
