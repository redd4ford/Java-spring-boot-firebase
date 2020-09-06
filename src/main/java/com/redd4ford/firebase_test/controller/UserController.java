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

  @GetMapping("/{email}")
  public ResponseEntity<UserDto> getUser(@PathVariable("email") String email)
      throws InterruptedException, ExecutionException {
    if (userService.getByEmail(email) != null) {
      log.info("GET    200 : " + email);
      return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);
    } else {
      log.error("GET    404 : " + email);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto,
                                            BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    if (bindingResult.hasErrors() || userService.getByEmail(userDto.getEmail()) != null) {
      log.error("CREATE 400 : " + userDto.getEmail());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      log.info("CREATE 200 : " + userDto.getEmail());
      return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
    }
  }

  @PutMapping("/{email}")
  public ResponseEntity<UserDto> updateUser(@PathVariable("email") String email,
                                            @RequestBody @Valid UserDto userDto,
                                            BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (userService.getByEmail(email) != null) {
      if (bindingResult.hasErrors()) {
        log.error("UPDATE 400 : " + userDto.getEmail());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      log.info("UPDATE 200 : " + userDto.getEmail());
      return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
    } else {
      log.error("UPDATE 404 : " + userDto.getEmail());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{email}")
  public ResponseEntity<Void> deleteUser(@PathVariable("email") String email)
      throws ExecutionException, InterruptedException {
    if (userService.getByEmail(email) != null) {
      userService.delete(email);
      log.info("DELETE 200 : " + email);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.error("DELETE 404 : " + email);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
