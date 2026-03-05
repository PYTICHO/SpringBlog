package com.thirdsteps.blog.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.thirdsteps.blog.db.Dto.PostRequestPostDto;
import com.thirdsteps.blog.db.Dto.PostRequestPutDto;
import com.thirdsteps.blog.db.entities.PostEntity;
import com.thirdsteps.blog.db.entities.PostEntityMapper;
import com.thirdsteps.blog.db.repositories.PostJpaRepository;
import com.thirdsteps.blog.statuses.PostStatus;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostJpaRepository repository;
    private final PostEntityMapper mapper;

    public PostEntity create(PostRequestPostDto request) {
        PostEntity entity = mapper.toPostEntity(request);
        entity.setPostStatus(PostStatus.PUBLISHED);
        entity.setViews(0L);
        return repository.save(entity);
    }

    public List<PostEntity> getAll() {
        return repository.findAll();
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


    @Transactional
    @Scheduled(fixedRate = 10000)
    public void increaseViews() {
        Long randomValue = ThreadLocalRandom.current().nextLong(5000, 20000);
        repository.increaseViews(randomValue);
    }


    public void delete(Long id) {
        PostEntity entity = repository.findById(id)
                .orElseThrow(() -> 
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Post with id=`%s` not found!".formatted(id)
                    )
                );

        repository.delete(entity);
    }

    

}
