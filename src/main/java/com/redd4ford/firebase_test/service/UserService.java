package com.redd4ford.firebase_test.service;

import com.google.firebase.database.annotations.NotNull;
import com.redd4ford.firebase_test.dto.UserDto;
import com.redd4ford.firebase_test.mapper.UserMapper;
import com.redd4ford.firebase_test.model.User;
import com.redd4ford.firebase_test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

  private final UserMapper userMapper;

  private final UserRepository userRepository = new UserRepository("users");

  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public List<UserDto> getAll()
      throws ExecutionException, InterruptedException {
    return userRepository.findAll()
        .stream()
        .map(userMapper::mapUserToDto)
        .collect(toList());
  }

  public UserDto getByEmail(String email)
      throws ExecutionException, InterruptedException {
    User user = userRepository.findById(email);
    return userMapper.mapUserToDto(user);
  }

  public UserDto save(@NotNull UserDto userDto) {
    User user = userRepository.save(userMapper.mapDtoToUser(userDto), userDto.getEmail());
    return userMapper.mapUserToDto(user);
  }

  public void delete(String email) {
    userRepository.deleteById(email);
  }

}
