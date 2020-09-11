package com.redd4ford.firebase_test.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Community {

  private Integer id;

  private String communityName;

  // ManyToMany
  private List<Integer> userIds;

  // OneToMany
  private List<Post> posts;

  public Community(Integer id, List<Integer> userIds, List<Post> posts, String communityName) {
    this.id = id;
    this.userIds = userIds;
    this.posts = posts;
    this.communityName = communityName;
  }

  public Community() {
  }

  public static CommunityBuilder builder() {
    return new CommunityBuilder();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Integer> getUserIds() {
    return userIds;
  }

  public void setUserIds(List<Integer> userIds) {
    this.userIds = userIds;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public String getCommunityName() {
    return communityName;
  }

  public void setCommunityName(String communityName) {
    this.communityName = communityName;
  }

  public static class CommunityBuilder {
    private Integer id;
    private List<Integer> userIds;
    private List<Post> posts;
    private String communityName;

    CommunityBuilder() {
    }

    public CommunityBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public CommunityBuilder userIds(List<Integer> userIds) {
      this.userIds = userIds;
      return this;
    }

    public CommunityBuilder posts(List<Post> posts) {
      this.posts = posts;
      return this;
    }

    public CommunityBuilder communityName(String communityName) {
      this.communityName = communityName;
      return this;
    }

    public Community build() {
      return new Community(id, userIds, posts, communityName);
    }

    public String toString() {
      return "Community.CommunityBuilder(id=" + this.id +
          ", userIds=" + this.userIds +
          ", posts=" + this.posts +
          ", communityName=" + this.communityName + ")";
    }

  }

}
