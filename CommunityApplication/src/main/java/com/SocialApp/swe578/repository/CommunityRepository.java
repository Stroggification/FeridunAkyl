package com.SocialApp.swe578.repository;

import com.SocialApp.swe578.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
        Community findByName(String name);
}
