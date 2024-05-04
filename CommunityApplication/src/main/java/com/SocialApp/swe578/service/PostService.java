package com.SocialApp.swe578.service;

import com.SocialApp.swe578.dto.PostDto;
import com.SocialApp.swe578.model.Post;

import java.util.List;

public interface PostService {

    Post createPost(PostDto postDto);

    void deletePost(String postName);

    List<Post> listAllPostsInCommunity(String communityName);


}
