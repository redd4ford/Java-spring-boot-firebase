package com.redd4ford.firebase_test.dto;

public class PostDto {

  private Integer id;
  private String authorUsername;
  private String communityName;
  private String text;

  public PostDto(Integer id, String authorUsername, String communityName, String text) {
    this.id = id;
    this.authorUsername = authorUsername;
    this.communityName = communityName;
    this.text = text;
  }

  public PostDto() {
  }

  public static PostDtoBuilder builder() {
    return new PostDtoBuilder();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAuthorUsername() {
    return authorUsername;
  }

  public void setAuthorUsername(String authorUsername) {
    this.authorUsername = authorUsername;
  }

  public String getCommunityName() {
    return communityName;
  }

  public void setCommunityName(String communityName) {
    this.communityName = communityName;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  public static class PostDtoBuilder {
    private Integer id;
    private String authorUsername;
    private String communityName;
    private String text;

    PostDtoBuilder() {
    }

    public PostDtoBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public PostDtoBuilder authorUsername(String authorUsername) {
      this.authorUsername = authorUsername;
      return this;
    }

    public PostDtoBuilder communityName(String communityName) {
      this.communityName = communityName;
      return this;
    }

    public PostDtoBuilder text(String text) {
      this.text = text;
      return this;
    }

    public PostDto build() {
      return new PostDto(id, authorUsername, communityName, text);
    }

    public String toString() {
      return "PostDto.PostDtoBuilder(id=" + this.id +
          ", authorUsername=" + this.authorUsername +
          ", communityName=" + this.communityName +
          ", text=" + this.text + ")";
    }
  }
}
