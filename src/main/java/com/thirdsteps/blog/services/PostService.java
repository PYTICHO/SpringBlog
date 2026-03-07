package com.thirdsteps.blog.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.thirdsteps.blog.db.dto.PostRequestPostDto;
import com.thirdsteps.blog.db.dto.PostRequestPutDto;
import com.thirdsteps.blog.db.entities.AuthorEntity;
import com.thirdsteps.blog.db.entities.PostEntity;
import com.thirdsteps.blog.db.mappers.PostEntityMapper;
import com.thirdsteps.blog.db.repositories.AuthorJpaRepository;
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

    private final PostJpaRepository postRepository;
    private final AuthorJpaRepository authorRepository;
    private final PostEntityMapper mapper;

    public PostEntity create(PostRequestPostDto request) {
        AuthorEntity author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> 
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found")
                );
        PostEntity entity = mapper.toPostEntity(request);
        entity.setAuthor(author);
        entity.setPostStatus(PostStatus.PUBLISHED);
        entity.setViews(0L);
        return postRepository.save(entity);
    }

    public List<PostEntity> getAll() {
        return postRepository.findAll();
    }

    public PostEntity getOne(Long id) {
        Optional<PostEntity> entityOptional = postRepository.findById(id);
        return entityOptional.orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id=`%s` not found!".formatted(id))
        );
    }

    public PostEntity updatePostFromDto(
        Long id, 
        PostRequestPutDto entityPutDto
    ) {
        Optional<PostEntity> entity = postRepository.findById(id);

        if (entity.isPresent()) {
            mapper.updatePostEntityFromRequestPutDto(entityPutDto, entity.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id=`%s` not found!".formatted(id));
        }

        PostEntity updatedEntity = postRepository.save(entity.get());
        
        return updatedEntity;
    };


    @Transactional
    @Scheduled(fixedRate = 10000)
    public void increaseViews() {
        Long randomValue = ThreadLocalRandom.current().nextLong(5000, 20000);
        postRepository.increaseViews(randomValue);
    }


    public void delete(Long id) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> 
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Post with id=`%s` not found!".formatted(id)
                    )
                );

        postRepository.delete(entity);
    }

    

}
