package com.redd4ford.firebase_test.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

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


  public UserDto(String username, String email, String location, String password) {
    this.username = username;
    this.email = email;
    this.location = location;
    this.password = password;
  }

  public UserDto() {
  }

  public static UserDtoBuilder builder() {
    return new UserDtoBuilder();
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

  public static class UserDtoBuilder {
    private String username;
    private String email;
    private String location;
    private String password;

    UserDtoBuilder() {
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

    public UserDto build() {
      return new UserDto(username, email, location, password);
    }

    public String toString() {
      return "UserDto.UserDtoBuilder(username=" + this.username +
          ", email=" + this.email +
          ", location=" + this.location +
          ", password=" + this.password + ")";
    }

  }

}
