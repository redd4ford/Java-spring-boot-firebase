package com.redd4ford.firebase_test.controller;

import com.redd4ford.firebase_test.dto.PostDto;
import com.redd4ford.firebase_test.model.Community;
import com.redd4ford.firebase_test.service.CommunityService;
import com.redd4ford.firebase_test.service.PostService;
import com.redd4ford.firebase_test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/posts")
public class PostController {

  private static final Logger log = LoggerFactory.getLogger(PostController.class);

  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  @Autowired
  private CommunityService communityService;

  @GetMapping
  public List<PostDto> getAll() throws InterruptedException, ExecutionException {
    return postService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getOne(@PathVariable("id") Integer id)
      throws InterruptedException, ExecutionException {
    if (postService.isExistById(id)) {
      log.info("GET    200 : id" + id);
      return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<PostDto> create(@RequestBody @Valid PostDto postDto,
                                            BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    postDto.setId(postService.getIdleId(postDto));
    if (bindingResult.hasErrors() ||
        !userService.isExistByUsername(postDto.getAuthorUsername()) ||
        !communityService.isExistByName(postDto.getCommunityName())) {
      log.error("CREATE 400 : id" + postDto.getId());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      log.info("CREATE 200 : id" + postDto.getId());
      postService.establishPostsUser(postDto);
      postService.establishPostsCommunity(postDto);
      return new ResponseEntity<>(postService.save(postDto, postDto.getId()), HttpStatus.OK);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostDto> update(@PathVariable("id") Integer id,
                                            @RequestBody @Valid PostDto postDto,
                                            BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (postService.isExistById(id)) {
      if (bindingResult.hasErrors()) {
        log.error("UPDATE 400 : id" + id);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      log.info("UPDATE 200 : id" + id);
      return new ResponseEntity<>(postService.save(postDto, postDto.getId()), HttpStatus.OK);
    } else {
      log.error("UPDATE 404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable("id") Integer id) {
    if (postService.isExistById(id)) {
      postService.delete(id);
      log.info("DELETE 200 : id" + id);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.error("DELETE 404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
