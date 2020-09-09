package com.redd4ford.firebase_test.mapper;

import com.redd4ford.firebase_test.dto.UserDto;
import com.redd4ford.firebase_test.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserMapper() {
  }

  public UserDto mapUserToDto(User user) {
    if (user == null) {
      return null;
    }

    UserDto.UserDtoBuilder userDto = UserDto.builder();

    userDto.id(user.getId());
    userDto.username(user.getUsername());
    userDto.email(user.getEmail());
    userDto.password(user.getPassword());
    userDto.location(user.getLocation());
    userDto.posts(user.getPosts());
    if (user.getPosts() != null) {
      userDto.postCounter(user.getPosts().size());
    } else {
      userDto.postCounter(0);
    }

    return userDto.build();
  }

  public User mapDtoToUser(UserDto userDto) {
    if (userDto == null) {
      return null;
    }

    User.UserBuilder user = User.builder();

    user.id(userDto.getId());
    user.username(userDto.getUsername());
    user.email(userDto.getEmail());
    user.location(userDto.getLocation());
    user.password(userDto.getPassword());

    return user.build();
  }

}
