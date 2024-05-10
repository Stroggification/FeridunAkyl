package com.SocialApp.swe578.service;

import com.SocialApp.swe578.dto.PostDto;
import com.SocialApp.swe578.model.Community;
import com.SocialApp.swe578.model.Post;
import com.SocialApp.swe578.model.User;
import com.SocialApp.swe578.repository.CommunityRepository;
import com.SocialApp.swe578.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommunityRepository communityRepository;
    private UserServiceImpl userService;

    @Autowired
    public PostServiceImp(UserServiceImpl userService) {
        this.userService = userService;
    }

    public PostServiceImp(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(PostDto postDto) {
        Date currentDate = new Date();
        User creator = userService.getAuthUser();
        Community postedCommunity = communityRepository.findByName(postDto.getPostCommunity());

        Post newPost = new Post(postDto.getTitle(), postDto.getDescription(), currentDate, creator, postedCommunity);
        return postRepository.save(newPost);

    }
    public List<Post> listAllPostsInCommunity(String communityName){
        List<Post> allPosts = postRepository.findAll();
        List<Post> communityPostList = new ArrayList<>();
        for (Post post : allPosts) {
            if (post.getPostCommunity().getName().equals(communityName)) {
                communityPostList.add(post);
            }
        }
        return communityPostList;
    }

    @Override
    public boolean deletePost(Long postId) {
        boolean isDeleted = false;
        User currentUser = userService.getAuthUser();
        Optional<Post> postOptional = postRepository.findById(postId);
        Post post = postOptional.orElse(null);
        if(currentUser.equals(post.getCreator())){
            postRepository.delete(post);
            isDeleted = true;
            return isDeleted;
        }else {
        return isDeleted;
        }
    }


}
