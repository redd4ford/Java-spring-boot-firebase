package com.redd4ford.firebase_test.repository;

import com.redd4ford.firebase_test.api.repository.FirebaseRepository;
import com.redd4ford.firebase_test.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository extends FirebaseRepository<User, Integer> {

  public UserRepository(String collectionName) {
    super(collectionName);
  }

  public User findByUsername(String username) throws ExecutionException, InterruptedException {
    List<User> users = findAll();
    for (User user: users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }

  public User findByEmail(String email) throws ExecutionException, InterruptedException {
    List<User> users = findAll();
    for (User user: users) {
      if (user.getEmail().equals(email)) {
        return user;
      }
    }
    return null;
  }

}
