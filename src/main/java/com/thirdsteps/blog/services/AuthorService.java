package com.thirdsteps.blog.services;

import java.util.List;

import com.thirdsteps.blog.db.dto.AuthorRequestPostDto;
import com.thirdsteps.blog.db.entities.AuthorEntity;
import com.thirdsteps.blog.db.mappers.AuthorEntityMapper;
import com.thirdsteps.blog.db.repositories.AuthorJpaRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorService {

    private final AuthorJpaRepository authorRepository;
    private final AuthorEntityMapper authorMapper;

    public AuthorEntity create(AuthorRequestPostDto request) {
        AuthorEntity entity = authorMapper.toAuthorEntity(request);
        AuthorEntity savedEntity = authorRepository.save(entity);

        return savedEntity;
    }

    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }
}
