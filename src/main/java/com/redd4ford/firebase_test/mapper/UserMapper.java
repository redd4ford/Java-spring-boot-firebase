package com.redd4ford.firebase_test.mapper;

import com.redd4ford.firebase_test.dto.UserDto;
import com.redd4ford.firebase_test.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDto mapUserToDto(User user) {
    if (user == null) {
      return null;
    }

    UserDto.UserDtoBuilder userDto = UserDto.builder();

    userDto.username(user.getUsername());
    userDto.email(user.getEmail());
    userDto.password(user.getPassword());
    userDto.location(user.getLocation());

    return userDto.build();
  }

  public User mapDtoToUser(UserDto userDto) {
    if (userDto == null) {
      return null;
    }

    User.UserBuilder user = User.builder();

    user.username(userDto.getUsername());
    user.email(userDto.getEmail());
    user.location(userDto.getLocation());
    user.password(userDto.getPassword());

    return user.build();
  }

}
