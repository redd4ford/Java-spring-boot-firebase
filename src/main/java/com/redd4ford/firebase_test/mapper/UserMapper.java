package com.redd4ford.firebase_test.mapper;

import com.redd4ford.firebase_test.dto.UserDto;
import com.redd4ford.firebase_test.model.User;
import com.redd4ford.firebase_test.repository.CommunityRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

  private final CommunityRepository communityRepository = new CommunityRepository("communities");

  public UserMapper() {
  }

  public UserDto mapObjectToDto(User user) {
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
    userDto.postCounter(getPostCounter(user));
    userDto.communityNames(getCommunityNames(user));

    return userDto.build();
  }

  public User mapDtoToObject(UserDto userDto) {
    if (userDto == null) {
      return null;
    }

    User.UserBuilder user = User.builder();

    user.id(userDto.getId());
    user.username(userDto.getUsername());
    user.email(userDto.getEmail());
    user.location(userDto.getLocation());
    user.password(userDto.getPassword());
    user.communityIds(getCommunityIds(userDto));

    return user.build();
  }

  private List<String> getCommunityNames(User user) {
    if (user.getCommunityIds() == null) {
      return null;
    }
    List<String> communityNamesList = new ArrayList<>();
    for (Integer id : user.getCommunityIds()) {
      try {
        communityNamesList.add(communityRepository.findById(id).getCommunityName());
      } catch (Exception e) {
        return null;
      }
    }
    return communityNamesList;
  }

  private List<Integer> getCommunityIds(UserDto userDto) {
    if (userDto.getCommunityNames() == null) {
      return null;
    }
    List<Integer> communityIdsList = new ArrayList<>();
    for (String name : userDto.getCommunityNames()) {
      try {
        communityIdsList.add(communityRepository.findByCommunityName(name).getId());
      } catch (Exception e) {
        return null;
      }
    }
    return communityIdsList;
  }

  private Integer getPostCounter(User user) {
    if (user.getPosts() != null) {
      return user.getPosts().size();
    } else {
      return 0;
    }
  }

}
