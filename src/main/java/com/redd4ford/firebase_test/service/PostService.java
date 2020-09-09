package com.redd4ford.firebase_test.service;

import com.google.firebase.database.annotations.NotNull;
import com.redd4ford.firebase_test.dto.PostDto;
import com.redd4ford.firebase_test.mapper.PostMapper;
import com.redd4ford.firebase_test.model.Post;
import com.redd4ford.firebase_test.model.User;
import com.redd4ford.firebase_test.repository.PostRepository;
import com.redd4ford.firebase_test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

  private final PostMapper postMapper;

  private final PostRepository postRepository = new PostRepository("posts");
  private final UserRepository userRepository = new UserRepository("users");

  public PostService(PostMapper postMapper) {
    this.postMapper = postMapper;
  }

  public List<PostDto> getAll()
      throws ExecutionException, InterruptedException {
    return postRepository.findAll()
        .stream()
        .map(postMapper::mapPostToDto)
        .collect(toList());
  }

  public PostDto getById(Integer id)
      throws ExecutionException, InterruptedException {
    Post post = postRepository.findById(id);
    return postMapper.mapPostToDto(post);
  }

  public PostDto save(@NotNull PostDto postDto) throws ExecutionException, InterruptedException {
    Post post = postRepository.save(postMapper.mapDtoToPost(postDto), postDto.getId());
    return postMapper.mapPostToDto(post);
  }

  public void delete(Integer id) {
    postRepository.deleteById(id);
  }

  public void setId(PostDto postDto) throws ExecutionException, InterruptedException {
    Integer i = postRepository.countAll() + 1;
    while (getById(i) != null) {
      i++;
    }
    postDto.setId(i);
  }

  public void establishManyToOne(PostDto postDto)
      throws ExecutionException, InterruptedException {
    Post post = postMapper.mapDtoToPost(postDto);
    User user = userRepository.findById(post.getUserId());
    List<Post> postList = new ArrayList<>();
    if (user.getPosts() != null) {
      postList = user.getPosts();
    }
    postList.add(post);
    user.setPosts(postList);

    userRepository.save(user, user.getId());
  }

}
