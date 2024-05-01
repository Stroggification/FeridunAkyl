package com.SocialApp.swe578.service;

import com.SocialApp.swe578.dto.CommunityDto;
import com.SocialApp.swe578.model.Community;
import com.SocialApp.swe578.model.User;
import com.SocialApp.swe578.repository.CommunityRepository;
import org.springframework.ui.Model;

import java.util.List;

public interface CommunityService {

    Community createCommunity(CommunityDto communityDto);
    //String getCommunityName(CommunityDto communityDto);
    void subscribeToCommunity(String communityName);
    void unsubscribeFromCommunity(String communityName);

    List<String> listSubscribers(String communityName);


    /*
    subscribeTo(User user, CommunityRepository communityRepository){

    public void unsubscribeFrom(User user, CommunityRepository communityRepository){
        communityRepository.getSubscribers().remove(user);
    }

    List<Community> getAllCommunities() { return userRepository.findAll()}
*/
}
