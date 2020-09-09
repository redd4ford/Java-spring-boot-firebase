package com.redd4ford.firebase_test.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Component
public class User {

  private Integer id;

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

  // OneToMany
  private List<Post> posts;

  public User(Integer id, String username, String email, String password, String location,
              List<Post> posts) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.location = location;
    this.posts = posts;
  }

  public User() {
  }

  public static UserBuilder builder() {
    return new UserBuilder();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public static class UserBuilder {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String location;
    private List<Post> posts;

    UserBuilder() {
    }

    public UserBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public UserBuilder username(String username) {
      this.username = username;
      return this;
    }

    public UserBuilder email(String email) {
      this.email = email;
      return this;
    }

    public UserBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UserBuilder location(String location) {
      this.location = location;
      return this;
    }

    public UserBuilder posts(List<Post> posts) {
      this.posts = posts;
      return this;
    }

    public User build() {
      return new User(id, username, email, password, location, posts);
    }

    public String toString() {
      return "User.UserBuilder(id=" + this.id +
          ", username=" + this.username +
          ", email=" + this.email +
          ", password=" + this.password +
          ", location=" + this.location + ")";
    }

  }

}
