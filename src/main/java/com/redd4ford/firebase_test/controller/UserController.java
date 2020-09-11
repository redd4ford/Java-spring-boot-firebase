package com.redd4ford.firebase_test.controller;

import com.redd4ford.firebase_test.dto.UserDto;
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
@RequestMapping("/users")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @GetMapping
  public List<UserDto> getAllUsers() throws InterruptedException, ExecutionException {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id)
      throws InterruptedException, ExecutionException {
    if (userService.getById(id) != null) {
      log.info("GET    200 : id" + id);
      return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto,
                                            BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    userDto.setId(userService.getIdleId(userDto));
    if (bindingResult.hasErrors() || userService.existsByEmail(userDto.getEmail()) ||
        userService.existsByUsername(userDto.getUsername())) {
      log.error("CREATE 400 : id" + userDto.getId());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      log.info("CREATE 200 : id" + userDto.getId());
      return new ResponseEntity<>(userService.save(userDto, userDto.getId()), HttpStatus.OK);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> updateUser(@PathVariable("id") Integer id,
                                            @RequestBody @Valid UserDto userDto,
                                            BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (userService.getById(id) != null) {
      if (bindingResult.hasErrors()) {
        log.error("UPDATE 400 : id" + id);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      log.info("UPDATE 200 : id" + id);
      userDto.setId(id);
      return new ResponseEntity<>(userService.save(userDto, userDto.getId()), HttpStatus.OK);
    } else {
      log.error("UPDATE 404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id)
      throws ExecutionException, InterruptedException {
    if (userService.getById(id) != null) {
      userService.delete(id);
      log.info("DELETE 200 : id" + id);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.error("DELETE 404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
