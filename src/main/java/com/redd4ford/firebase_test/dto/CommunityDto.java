package com.redd4ford.firebase_test.dto;

import com.redd4ford.firebase_test.model.Post;

import java.util.List;

public class CommunityDto {

  private Integer id;
  private String communityName;
  private List<Post> posts;
  private List<String> users;

  public CommunityDto(Integer id, String communityName, List<Post> posts, List<String> users) {
    this.id = id;
    this.communityName = communityName;
    this.posts = posts;
    this.users = users;
  }

  public CommunityDto() {
  }

  public static CommunityDtoBuilder builder() {
    return new CommunityDtoBuilder();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCommunityName() {
    return communityName;
  }

  public void setCommunityName(String communityName) {
    this.communityName = communityName;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public List<String> getUsers() {
    return users;
  }

  public void setUsers(List<String> users) {
    this.users = users;
  }

  public static class CommunityDtoBuilder {
    private Integer id;
    private String communityName;
    private List<Post> posts;
    private List<String> users;

    CommunityDtoBuilder() {
    }

    public CommunityDtoBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public CommunityDtoBuilder communityName(String communityName) {
      this.communityName = communityName;
      return this;
    }

    public CommunityDtoBuilder posts(List<Post> posts) {
      this.posts = posts;
      return this;
    }

    public CommunityDtoBuilder users(List<String> users) {
      this.users = users;
      return this;
    }

    public CommunityDto build() {
      return new CommunityDto(id, communityName, posts, users);
    }

    public String toString() {
      return "CommunityDto.CommunityDtoBuilder(id=" + this.id +
          ", communityName=" + this.communityName +
          ", posts=" + this.posts +
          ", users=" + this.users + ")";
    }

  }

}
