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
import org.springframework.ui.Model;

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
    public CommunityServiceImp(UserServiceImpl userService) {
        this.userService = userService;
    }

    public CommunityServiceImp(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public Community createCommunity(CommunityDto communityDto) {
        User owner = userService.getAuthUser();
        List<User> subscribers = new ArrayList<>();
        subscribers.add(owner);
        Community newCommunity = new Community(communityDto.getName(), communityDto.getDescription(), owner, subscribers);
        return communityRepository.save(newCommunity);
    }

    @Override
    public void subscribeToCommunity(String communityName) {
        User currentUser = userService.getAuthUser();
        Community community = communityRepository.findByName(communityName);
        List<User> subscribersList = community.getSubscribers();
        if (!subscribersList.contains(currentUser)) {
            community.getSubscribers().add(currentUser);
            communityRepository.save(community);
        }

    }

    @Override
    public void unsubscribeFromCommunity(String communityName) {
        User currentUser = userService.getAuthUser();
        Community community = communityRepository.findByName(communityName);
        List<User> subscribersList = community.getSubscribers();
        if (subscribersList.contains(currentUser)) {
            community.getSubscribers().remove(currentUser);
            communityRepository.save(community);
        }
    }

    @Override
    public List<String> listSubscribers(String communityName) {
        List<User> userList = communityRepository.findByName(communityName).getSubscribers();
        List<String> userNameList = new ArrayList<>();
        for (User user : userList) {
            userNameList.add(user.getEmail());
        }
        return userNameList;
    }

    ;

    @Override
    public boolean deleteCommunity(String communityName) {
        boolean isDeleted = false;
        User currentUser = userService.getAuthUser();
        Community community = communityRepository.findByName(communityName);
        if (community != null && community.getOwner().equals(currentUser)) {
            communityRepository.delete(community);
            isDeleted = true;
            return isDeleted;
        } else {
            return isDeleted;
        }
    }

    @Override
    public void removeUserFromCommunities() {
        User user = userService.getAuthUser();
        List<Community> communities = user.getSubscribedCommunities();
        for (Community community : communities) {
            community.getSubscribers().remove(user);
            communityRepository.save(community);
        }
    }
}