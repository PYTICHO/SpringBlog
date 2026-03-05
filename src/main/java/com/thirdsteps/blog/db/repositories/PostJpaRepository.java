package com.thirdsteps.blog.db.repositories;

import com.thirdsteps.blog.db.entities.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
    @Modifying
    @Query(value = "UPDATE blogs SET views = views + :randomValue", nativeQuery = true)
    void increaseViews(@Param("randomValue") Long randomValue);
}
