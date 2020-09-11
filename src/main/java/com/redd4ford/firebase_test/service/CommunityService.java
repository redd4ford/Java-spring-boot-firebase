package com.redd4ford.firebase_test.service;

import com.redd4ford.firebase_test.api.repository.FirebaseRepository;
import com.redd4ford.firebase_test.dto.CommunityDto;
import com.redd4ford.firebase_test.mapper.AbstractMapper;
import com.redd4ford.firebase_test.mapper.CommunityMapper;
import com.redd4ford.firebase_test.model.Community;
import com.redd4ford.firebase_test.model.User;
import com.redd4ford.firebase_test.repository.CommunityRepository;
import com.redd4ford.firebase_test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CommunityService extends AbstractService<Community, CommunityDto> {

  private final CommunityMapper communityMapper;
  private final CommunityRepository communityRepository = new CommunityRepository("communities");
  private final UserRepository userRepository = new UserRepository("users");

  public CommunityService(CommunityMapper communityMapper) {
    this.communityMapper = communityMapper;
  }

  @Override
  protected FirebaseRepository<Community, Integer> getRepository() {
    return communityRepository;
  }

  @Override
  protected AbstractMapper<Community, CommunityDto> getMapper() {
    return communityMapper;
  }

  public boolean isExistByName(String name)
      throws ExecutionException, InterruptedException {
    return communityRepository.findByCommunityName(name) != null;
  }

  // communities - users
  public void establishManyToMany(CommunityDto communityDto) {
    Community community = communityMapper.mapDtoToObject(communityDto);
    for (Integer id : community.getUserIds()) {
      try {
        User user = userRepository.findById(id);
        List<Integer> listOfCommunityIdsOfUser = user.getCommunityIds();
        if (!listOfCommunityIdsOfUser.contains(community.getId())) {
          listOfCommunityIdsOfUser.add(community.getId());
          user.setCommunityIds(listOfCommunityIdsOfUser);
          userRepository.save(user, user.getId());
        }
      } catch (Exception e) {
        System.err.println(e);
      }
    }
  }

}
