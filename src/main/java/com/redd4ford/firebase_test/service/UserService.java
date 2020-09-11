package com.redd4ford.firebase_test.service;

import com.redd4ford.firebase_test.api.repository.FirebaseRepository;
import com.redd4ford.firebase_test.dto.UserDto;
import com.redd4ford.firebase_test.mapper.AbstractMapper;
import com.redd4ford.firebase_test.mapper.UserMapper;
import com.redd4ford.firebase_test.model.User;
import com.redd4ford.firebase_test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService extends AbstractService<User, UserDto> {

  private final UserMapper userMapper;
  private final UserRepository userRepository = new UserRepository("users");

  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  protected FirebaseRepository<User, Integer> getRepository() {
    return userRepository;
  }

  @Override
  protected AbstractMapper<User, UserDto> getMapper() {
    return userMapper;
  }

  public boolean isExistByEmail(String email)
      throws ExecutionException, InterruptedException {
    return userRepository.findByEmail(email) != null;
  }

  public boolean isExistByUsername(String username)
      throws ExecutionException, InterruptedException {
    return userRepository.findByUsername(username) != null;
  }

}
