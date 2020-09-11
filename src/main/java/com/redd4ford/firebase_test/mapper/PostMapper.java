package com.redd4ford.firebase_test.mapper;

import com.redd4ford.firebase_test.dto.PostDto;
import com.redd4ford.firebase_test.model.Post;
import com.redd4ford.firebase_test.repository.CommunityRepository;
import com.redd4ford.firebase_test.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class PostMapper extends AbstractMapper<Post, PostDto> {

  private final UserRepository userRepository = new UserRepository("users");
  private final CommunityRepository communityRepository = new CommunityRepository("communities");

  public PostMapper() {
  }

  public PostDto mapObjectToDto(Post post) {
    if (post == null) {
      return null;
    }

    PostDto.PostDtoBuilder postDto = PostDto.builder();

    postDto.id(post.getId());
    postDto.authorUsername(getAuthorUsername(post));
    postDto.communityName(getCommunityName(post));
    postDto.text(post.getText());

    return postDto.build();
  }

  public Post mapDtoToObject(PostDto postDto) throws ExecutionException, InterruptedException {
    if (postDto == null) {
      return null;
    }

    Post.PostBuilder post = Post.builder();

    post.id(postDto.getId());
    post.userId(getUserId(postDto));
    post.communityId(getCommunityId(postDto));
    post.text(postDto.getText());

    return post.build();
  }

  private String getAuthorUsername(Post post) {
    try {
      return userRepository.findById(post.getUserId()).getUsername();
    } catch (Exception e) {
      System.err.println(e);
    }
    return null;
  }

  private String getCommunityName(Post post) {
    try {
      return communityRepository.findById(post.getCommunityId()).getCommunityName();
    } catch (Exception e) {
      return null;
    }
  }

  private Integer getUserId(PostDto postDto) throws ExecutionException, InterruptedException {
    return userRepository.findByUsername(postDto.getAuthorUsername()).getId();
  }

  private Integer getCommunityId(PostDto postDto) throws ExecutionException, InterruptedException {
    return communityRepository.findByCommunityName(postDto.getCommunityName()).getId();
  }

}
