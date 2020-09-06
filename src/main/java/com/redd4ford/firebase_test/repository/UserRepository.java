package com.redd4ford.firebase_test.repository;

import com.redd4ford.firebase_test.api.repository.FirebaseRepository;
import com.redd4ford.firebase_test.model.User;

public class UserRepository extends FirebaseRepository<User, String> {

  public UserRepository(String collectionName) {
    super(collectionName);
  }

}
