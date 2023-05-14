package com.car.project.car.project.user;


import com.car.project.car.project.security.PasswordHashing;
import com.jayway.jsonpath.InvalidCriteriaException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.Optional;


@Service
@NoArgsConstructor
public class UserService {

    @Autowired
    public UserRepository repository;

    public void create(User user) {

        try {

            User usernameCheck = repository.findByuserName(user.getUserName());
            if (usernameCheck != null) {
                throw new InstanceAlreadyExistsException("Username already exists");
            } else {
                User newUser = new User();
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setUserName(user.getUserName());
                newUser.setPassword(PasswordHashing.hashPassword(user.getPassword()));
                repository.save(newUser);
            }

        } catch (RuntimeException | InstanceAlreadyExistsException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public void delete(Long userId) {

        try {
            Optional<User> user = repository.findById(userId);

            if (user.isEmpty()) {
                throw new ChangeSetPersister.NotFoundException();
            } else {
                repository.delete(user.get());
            }
        } catch (ChangeSetPersister.NotFoundException e) {
            System.out.println("User not found");
        }

    }

    public User login(User user) {

        try {
            User result = repository.findByuserName(user.getUserName());
            if (result.getPassword().equals(PasswordHashing.hashPassword(user.getPassword()))) {
                return result;
            }

        } catch (RuntimeException e) {
            throw new InvalidCriteriaException();
        }
        return null;
    }

}
