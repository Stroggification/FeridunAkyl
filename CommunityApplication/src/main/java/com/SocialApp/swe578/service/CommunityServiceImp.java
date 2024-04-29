package com.SocialApp.swe578.service;

import com.SocialApp.swe578.dto.CommunityDto;
import com.SocialApp.swe578.dto.UserRegistrationDto;
import com.SocialApp.swe578.model.Community;
import com.SocialApp.swe578.model.User;
import com.SocialApp.swe578.repository.CommunityRepository;
import com.SocialApp.swe578.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityServiceImp implements CommunityService {
@Autowired
    private CommunityRepository communityRepository;
@Autowired
    private UserRepository userRepository;

    private UserServiceImpl userService;
    @Autowired
    public CommunityServiceImp(UserServiceImpl userService) {this.userService = userService;}

    public CommunityServiceImp(CommunityRepository communityRepository){this.communityRepository = communityRepository;}

   @Override
    public Community createCommunity(CommunityDto communityDto){
        User owner = userService.getAuthUser();
        List<User> subscribers = new ArrayList<>();
        subscribers.add(owner);
        Community newCommunity = new Community(communityDto.getName(), communityDto.getDescription(), owner, subscribers);
        return communityRepository.save(newCommunity);
    }
public String getCommunityName(CommunityDto communityDto){
        return communityDto.getName();
}

    /*
    public Community getCommunityName(Long id){
        return communityRepository.findById(id).orElseThrow();
    }
*/
}
