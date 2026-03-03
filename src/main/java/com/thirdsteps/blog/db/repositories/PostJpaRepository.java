package com.thirdsteps.blog.db.repositories;

import com.thirdsteps.blog.db.entities.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
    
}
