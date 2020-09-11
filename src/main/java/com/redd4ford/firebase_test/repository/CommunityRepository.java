package com.redd4ford.firebase_test.repository;

import com.redd4ford.firebase_test.api.repository.FirebaseRepository;
import com.redd4ford.firebase_test.model.Community;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CommunityRepository extends FirebaseRepository<Community, Integer> {

  public CommunityRepository(String collectionName) {
    super(collectionName);
  }

  public Community findByCommunityName(String name)
      throws ExecutionException, InterruptedException {
    List<Community> communities = findAll();
    for (Community community : communities) {
      if (community.getCommunityName().equals(name)) {
        return community;
      }
    }
    return null;
  }

}
