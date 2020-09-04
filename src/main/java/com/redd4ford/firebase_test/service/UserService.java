package com.redd4ford.firebase_test.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.NotNull;
import com.redd4ford.firebase_test.dto.UserDto;
import com.redd4ford.firebase_test.mapper.UserMapper;
import com.redd4ford.firebase_test.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

  private final Firestore database = FirestoreClient.getFirestore();
  private final UserMapper userMapper;

  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public UserDto save(@NotNull UserDto userDto) {
    database
        .collection("users")
        .document(userDto.getEmail())
        .set(userMapper.mapDtoToUser(userDto));

    return userDto;

  }

  public List<UserDto> getAll() throws ExecutionException, InterruptedException {
    List<User> userList = new ArrayList<>();
    CollectionReference users = database.collection("users");

    ApiFuture<QuerySnapshot> querySnapshot = users.get();
    for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
      User user = doc.toObject(User.class);
      userList.add(user);
    }

    return userList
        .stream()
        .map(userMapper::mapUserToDto)
        .collect(toList());
  }

  public UserDto getByEmail(String email) throws ExecutionException, InterruptedException {
    DocumentReference docRef = database.collection("users").document(email);
    // asynchronously retrieve the document
    ApiFuture<DocumentSnapshot> future = docRef.get();
    // block on response
    DocumentSnapshot document = future.get();

    assert document.exists() : "No user found";
    // convert document to POJO
    User user = document.toObject(User.class);

    return userMapper.mapUserToDto(user);
  }

  public void delete(String email) {
    database.collection("users").document(email).delete();
  }

}
