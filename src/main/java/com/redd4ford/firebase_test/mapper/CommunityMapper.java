package com.redd4ford.firebase_test.mapper;

import com.redd4ford.firebase_test.dto.CommunityDto;
import com.redd4ford.firebase_test.model.Community;
import com.redd4ford.firebase_test.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommunityMapper extends AbstractMapper<Community, CommunityDto> {

  private final UserRepository userRepository = new UserRepository("users");

  public CommunityMapper() {
  }

  public CommunityDto mapObjectToDto(Community community) {
    if (community == null) {
      return null;
    }

    CommunityDto.CommunityDtoBuilder communityDto = CommunityDto.builder();

    communityDto.id(community.getId());
    communityDto.communityName(community.getCommunityName());
    communityDto.posts(community.getPosts());
    communityDto.users(getUsers(community));

    return communityDto.build();
  }

  public Community mapDtoToObject(CommunityDto communityDto) {
    if (communityDto == null) {
      return null;
    }

    Community.CommunityBuilder community = Community.builder();

    community.id(communityDto.getId());
    community.communityName(communityDto.getCommunityName());
    community.posts(communityDto.getPosts());
    community.userIds(getUserIds(communityDto));

    return community.build();
  }

  private List<String> getUsers(Community community) {
    if (community.getUserIds() == null) {
      return null;
    }
    List<String> users = new ArrayList<>();
    for (Integer id : community.getUserIds()) {
      try {
        users.add(userRepository.findById(id).getUsername());
      } catch (Exception e) {
        return null;
      }
    }
    return users;
  }

  private List<Integer> getUserIds(CommunityDto communityDto) {
    if (communityDto.getUsers() == null) {
      return null;
    }
    List<Integer> userIdsList = new ArrayList<>();
    for (String username : communityDto.getUsers()) {
      try {
        userIdsList.add(userRepository.findByUsername(username).getId());
      } catch (Exception e) {
        return null;
      }
    }
    return userIdsList;
  }

}
