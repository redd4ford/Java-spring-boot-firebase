package com.redd4ford.firebase_test.dto;

import com.redd4ford.firebase_test.model.Post;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserDto {

  private int id;

  @NotBlank(message = "Username cannot be null")
  private String username;

  @Email(message = "Email is not correct")
  @NotBlank(message = "Email cannot be null")
  private String email;

  @NotBlank(message = "Password cannot be null")
  @Length(min = 8, message = "Password is too short")
  private String password;

  @NotBlank(message = "Location cannot be null")
  private String location;

  private Integer postCounter;

  private List<Post> posts;

  public UserDto(int id, String username, String email, String location, String password,
                 List<Post> posts, Integer postCounter) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.location = location;
    this.password = password;
    this.posts = posts;
    this.postCounter = postCounter;
  }

  public UserDto() {
  }

  public static UserDtoBuilder builder() {
    return new UserDtoBuilder();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public Integer getPostCounter() {
    return postCounter;
  }

  public void setPostCounter(Integer postCounter) {
    this.postCounter = postCounter;
  }

  public static class UserDtoBuilder {
    private int id;
    private String username;
    private String email;
    private String location;
    private String password;
    private Integer postCounter;
    private List<Post> posts;

    UserDtoBuilder() {
    }

    public UserDtoBuilder id(int id) {
      this.id = id;
      return this;
    }

    public UserDtoBuilder username(String username) {
      this.username = username;
      return this;
    }

    public UserDtoBuilder email(String email) {
      this.email = email;
      return this;
    }

    public UserDtoBuilder location(String location) {
      this.location = location;
      return this;
    }

    public UserDtoBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UserDtoBuilder posts(List<Post> posts) {
      this.posts = posts;
      return this;
    }

    public UserDtoBuilder postCounter(Integer postCounter) {
      this.postCounter = postCounter;
      return this;
    }

    public UserDto build() {
      return new UserDto(id, username, email, location, password, posts, postCounter);
    }

    public String toString() {
      return "UserDto.UserDtoBuilder(id=" + this.id +
          ", username=" + this.username +
          ", email=" + this.email +
          ", location=" + this.location +
          ", password=" + this.password +
          ", postCounter=" + this.postCounter + ")";
    }

  }

}
