package com.redd4ford.firebase_test.mapper;

import com.redd4ford.firebase_test.dto.PostDto;
import com.redd4ford.firebase_test.model.Post;
import com.redd4ford.firebase_test.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class PostMapper {

  private final UserRepository userRepository = new UserRepository("users");

  public PostMapper() {
  }

  public PostDto mapPostToDto(Post post) {
    if (post == null) {
      return null;
    }

    PostDto.PostDtoBuilder postDto = PostDto.builder();

    postDto.id(post.getId());
    try {
      postDto.authorUsername(userRepository.findById(post.getUserId()).getUsername());
    } catch (Exception e) {
      System.err.println(e);
    }
    postDto.text(post.getText());

    return postDto.build();
  }

  public Post mapDtoToPost(PostDto postDto) throws ExecutionException, InterruptedException {
    if (postDto == null) {
      return null;
    }

    Post.PostBuilder post = Post.builder();

    post.id(postDto.getId());
    post.userId(userRepository.findByUsername(postDto.getAuthorUsername()).getId());
    post.text(postDto.getText());

    return post.build();
  }

}
