package com.thirdsteps.blog.db;

import java.util.List;
import java.util.Optional;

import com.thirdsteps.blog.db.entities.PostEntity;
import com.thirdsteps.blog.db.entities.PostEntityMapper;
import com.thirdsteps.blog.db.entities.Dto.PostDto;
import com.thirdsteps.blog.db.entities.Dto.PostRequestPostDto;
import com.thirdsteps.blog.db.entities.Dto.PostRequestPutDto;
import com.thirdsteps.blog.db.repositories.PostJpaRepository;
import com.thirdsteps.blog.db.statuses.PostStatus;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostProcessor {

    private final PostJpaRepository repository;
    private final PostEntityMapper mapper;

    public PostEntity create(PostRequestPostDto request) {
        PostEntity entity = mapper.toPostEntity(request);
        entity.setPostStatus(PostStatus.PUBLISHED);
        entity.setViews(0);
        return repository.save(entity);
    }

    public List<PostDto> getAll() {
        List<PostEntity> posts = repository.findAll();
        List<PostDto> postsDto = mapper.toPostDtoList(posts);
        return postsDto;
    }

    public PostEntity getOne(Long id) {
        Optional<PostEntity> entityOptional = repository.findById(id);
        return entityOptional.orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id=`%s` not found!".formatted(id))
        );
    }

    public PostEntity updatePostFromDto(
        Long id, 
        PostRequestPutDto entityPutDto
    ) {
        Optional<PostEntity> entity = repository.findById(id);

        if (entity.isPresent()) {
            mapper.updatePostEntityFromRequestPutDto(entityPutDto, entity.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id=`%s` not found!".formatted(id));
        }

        PostEntity updatedEntity = repository.save(entity.get());
        
        return updatedEntity;
    };

    

}
