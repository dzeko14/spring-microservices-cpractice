package com.github.dzeko14.SocialNetwork.repository;

import com.github.dzeko14.SocialNetwork.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
