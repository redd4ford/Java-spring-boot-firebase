package com.redd4ford.firebase_test.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
public class Post {

  private Integer id;

  @NotBlank(message = "Message cannot be empty")
  @Length(min = 15, message = "Message should contain at least 15 symbols")
  private String text;

  // ManyToOne
  private Integer userId;

  // ManyToOne
  private Integer communityId;

  public Post(Integer id, Integer userId, Integer communityId, String text) {
    this.id = id;
    this.userId = userId;
    this.communityId = communityId;
    this.text = text;
  }

  public Post() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getCommunityId() {
    return communityId;
  }

  public void setCommunityId(Integer communityId) {
    this.communityId = communityId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public static PostBuilder builder() {
    return new PostBuilder();
  }

  public static class PostBuilder {
    public Integer id;
    private String text;
    public Integer userId;
    public Integer communityId;

    PostBuilder() {
    }

    public PostBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public PostBuilder userId(Integer userId) {
      this.userId = userId;
      return this;
    }

    public PostBuilder communityId(Integer communityId) {
      this.communityId = communityId;
      return this;
    }

    public PostBuilder text(String text) {
      this.text = text;
      return this;
    }

    public Post build() {
      return new Post(id, userId, communityId, text);
    }

    public String toString() {
      return "Post.PostBuilder(id=" + this.id +
          ", userId=" + this.userId +
          ", communityId=" + this.communityId +
          ", text=" + this.text + ")";
    }

  }

}
