package com.thirdsteps.blog.db.repositories;

import com.thirdsteps.blog.db.entities.AuthorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, Long>{

}
