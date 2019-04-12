package com.github.dzeko14.SocialNetwork.service;

import com.github.dzeko14.SocialNetwork.exception.DatabaseNotRespondException;
import com.github.dzeko14.SocialNetwork.exception.InvalidEntityFieldException;
import com.github.dzeko14.SocialNetwork.exception.ResourceNotFoundException;
import com.github.dzeko14.SocialNetwork.model.User;
import com.github.dzeko14.SocialNetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getUserList() throws DatabaseNotRespondException {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }

    public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("There is no user with this id"));
    }

    public User createUser(User user) throws DatabaseNotRespondException {

        if(user.getName() == null)  throw new InvalidEntityFieldException("Field 'name' is not correct!");
        if (user.getSurname() == null) throw new InvalidEntityFieldException("Field 'surname' is not correct!");
        if (user.getEmail() == null)  throw new InvalidEntityFieldException("Field 'email' is not correct!");
        if(user.getPassword() == null) throw new InvalidEntityFieldException("Field 'password' is not correct!");

        try {
            return userRepository.save(user);
        }  catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }

    }

    public User updateUser(long id, User updatedUser) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with this id") );

        User newUser = User.merge(oldUser, updatedUser);
        try {
            return userRepository.save(newUser);
        }  catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }

    public void deleteUserById(long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseNotRespondException(e.getMessage());
        }
    }
}
