package com.thirdsteps.blog.db;

import java.util.List;

import com.thirdsteps.blog.db.entities.PostDto;
import com.thirdsteps.blog.db.entities.PostEntity;
import com.thirdsteps.blog.db.entities.PostEntityMapper;
import com.thirdsteps.blog.db.entities.PostRequestDto;
import com.thirdsteps.blog.db.repositories.PostJpaRepository;
import com.thirdsteps.blog.db.statuses.PostStatus;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostProcessor {

    private final PostJpaRepository repository;
    private final PostEntityMapper mapper;

    public PostEntity create(PostRequestDto request) {
        PostEntity entity = mapper.toPostEntity(request);
        entity.setPostStatus(PostStatus.PUBLISHED);
        entity.setViews(0);
        return repository.save(entity);
    }

    public List<PostDto> getAll() {
        List<PostEntity> posts = repository.findAll();
        List<PostDto> postsDto = mapper.toPostDtoList(posts);
        return postsDto;
    };

}
