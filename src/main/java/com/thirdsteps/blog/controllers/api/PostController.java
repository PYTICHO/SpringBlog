package com.thirdsteps.blog.controllers.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.thirdsteps.blog.db.PostProcessor;
import com.thirdsteps.blog.db.entities.PostEntity;
import com.thirdsteps.blog.db.entities.PostEntityMapper;
import com.thirdsteps.blog.db.entities.Dto.PostDto;
import com.thirdsteps.blog.db.entities.Dto.PostRequestPostDto;
import com.thirdsteps.blog.db.entities.Dto.PostRequestPutDto;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;





@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostProcessor postProcessor;
    private final PostEntityMapper postEntityMapper;

    @PostMapping()
    public PostDto create(
        @RequestBody PostRequestPostDto request
    ) {
        log.info("Creating post: {}", request);
        PostEntity entity = postProcessor.create(request);
        PostDto entityDto = postEntityMapper.toPostDto(entity);
        return entityDto;
    }

    @GetMapping()
    public List<PostDto> getAllPosts() {
        log.info("Return all posts");
        List<PostDto> posts = postProcessor.getAll();
        return posts;
    }


    @GetMapping("/{id}")
    public PostDto getMethodName(
        @PathVariable Long id
    ) {
        log.info("Return post with id={}", id);
        PostEntity postEntity = postProcessor.getOne(id);
        PostDto postDto = postEntityMapper.toPostDto(postEntity);

        return postDto;
    }
    
    @PutMapping("/{id}")
    public PostDto update(
        @PathVariable Long id,
        @RequestBody PostRequestPutDto entityPutDto
    ) {
        log.info("Update post with id={}", id);
        PostEntity updatedPostEntity = postProcessor.updatePostFromDto(id, entityPutDto);
        PostDto postDto = postEntityMapper.toPostDto(updatedPostEntity);

        return postDto;
    }
    
    
}
