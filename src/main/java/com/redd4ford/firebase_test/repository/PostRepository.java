package com.redd4ford.firebase_test.repository;

import com.redd4ford.firebase_test.api.repository.FirebaseRepository;
import com.redd4ford.firebase_test.model.Post;

public class PostRepository extends FirebaseRepository<Post, Integer> {

  public PostRepository(String collectionName) {
    super(collectionName);
  }

}
