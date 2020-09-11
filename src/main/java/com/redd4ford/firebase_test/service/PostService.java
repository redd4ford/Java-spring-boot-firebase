package com.redd4ford.firebase_test.service;

import com.redd4ford.firebase_test.api.repository.FirebaseRepository;
import com.redd4ford.firebase_test.dto.PostDto;
import com.redd4ford.firebase_test.mapper.AbstractMapper;
import com.redd4ford.firebase_test.mapper.PostMapper;
import com.redd4ford.firebase_test.model.Community;
import com.redd4ford.firebase_test.model.Post;
import com.redd4ford.firebase_test.model.User;
import com.redd4ford.firebase_test.repository.CommunityRepository;
import com.redd4ford.firebase_test.repository.PostRepository;
import com.redd4ford.firebase_test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PostService extends AbstractService<Post, PostDto> {

  private final PostMapper postMapper;
  private final PostRepository postRepository = new PostRepository("posts");
  private final UserRepository userRepository = new UserRepository("users");
  private final CommunityRepository communityRepository = new CommunityRepository("communities");

  public PostService(PostMapper postMapper) {
    this.postMapper = postMapper;
  }

  @Override
  protected FirebaseRepository<Post, Integer> getRepository() {
    return postRepository;
  }

  @Override
  protected AbstractMapper<Post, PostDto> getMapper() {
    return postMapper;
  }

  // posts - user
  public void establishPostsUser(PostDto postDto)
      throws ExecutionException, InterruptedException {
    Post post = postMapper.mapDtoToObject(postDto);
    User user = userRepository.findById(post.getUserId());
    List<Post> postList = new ArrayList<>();
    if (user.getPosts() != null) {
      postList = user.getPosts();
    }
    postList.add(post);
    user.setPosts(postList);

    userRepository.save(user, user.getId());
  }

  // posts - community
  public void establishPostsCommunity(PostDto postDto)
      throws ExecutionException, InterruptedException {
    Post post = postMapper.mapDtoToObject(postDto);
    Community community = communityRepository.findById(post.getCommunityId());
    List<Post> postList = new ArrayList<>();
    if (community.getPosts() != null) {
      postList = community.getPosts();
    }
    postList.add(post);
    community.setPosts(postList);

    communityRepository.save(community, community.getId());
  }

}
