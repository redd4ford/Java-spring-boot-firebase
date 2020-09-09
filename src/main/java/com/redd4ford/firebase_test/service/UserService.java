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

  public List<UserDto> getAll() throws ExecutionException, InterruptedException {
    return userRepository.findAll()
        .stream()
        .map(userMapper::mapUserToDto)
        .collect(toList());
  }

  public UserDto getById(Integer id) throws ExecutionException, InterruptedException {
    User user = userRepository.findById(id);
    return userMapper.mapUserToDto(user);
  }

  public UserDto save(@NotNull UserDto userDto) {
    User user = userRepository.save(userMapper.mapDtoToUser(userDto), userDto.getId());
    return userMapper.mapUserToDto(user);
  }

  public void delete(Integer id) {
    userRepository.deleteById(id);
  }

  public boolean existsByEmail(String email)
      throws ExecutionException, InterruptedException {
    return userRepository.findByEmail(email) != null;
  }

  public boolean existsByUsername(String username)
      throws ExecutionException, InterruptedException {
    return userRepository.findByUsername(username) != null;
  }

  public void setId(UserDto userDto) throws ExecutionException, InterruptedException {
    Integer i = userRepository.countAll() + 1;
    while (getById(i) != null) {
      i++;
    }
    userDto.setId(i);
  }

}
