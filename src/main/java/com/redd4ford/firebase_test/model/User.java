package com.redd4ford.firebase_test.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Component
public class User {

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

  public User(String username, String email, String password, String location) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.location = location;
  }

  public User() {
  }

  public static UserBuilder builder() {
    return new UserBuilder();
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

  public static class UserBuilder {
    private String username;
    private String email;
    private String password;
    private String location;

    UserBuilder() {
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

    public User build() {
      return new User(username, email, password, location);
    }

    public String toString() {
      return "User.UserBuilder(username=" + this.username +
          ", email=" + this.email +
          ", password=" + this.password +
          ", location=" + this.location + ")";
    }

  }

}
