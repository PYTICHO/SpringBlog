package com.thirdsteps.blog.controllers.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.thirdsteps.blog.db.dto.PostDto;
import com.thirdsteps.blog.db.dto.PostRequestPostDto;
import com.thirdsteps.blog.db.dto.PostRequestPutDto;
import com.thirdsteps.blog.db.entities.PostEntity;
import com.thirdsteps.blog.db.mappers.PostEntityMapper;
import com.thirdsteps.blog.services.PostService;

import org.springframework.web.bind.annotation.*;





@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postProcessor;
    private final PostEntityMapper postEntityMapper;


    @PostMapping()
    public PostDto create(
        @RequestBody PostRequestPostDto request
    ) {
        PostEntity entity = postProcessor.create(request);
        PostDto entityDto = postEntityMapper.toPostDto(entity);
        log.info("Creating post: {}", request);

        return entityDto;
    }


    @GetMapping()
    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postProcessor.getAll();
        List<PostDto> postsDto = postEntityMapper.toPostDtoList(posts);
        log.info("Return all posts");
        
        return postsDto;
    }


    @GetMapping("/{id}")
    public PostDto getMethodName(
        @PathVariable Long id
    ) {
        PostEntity postEntity = postProcessor.getOne(id);
        PostDto postDto = postEntityMapper.toPostDto(postEntity);
        log.info("Return post with id={}", id);

        return postDto;
    }

    
    @PutMapping("/{id}")
    public PostDto update(
        @PathVariable Long id,
        @RequestBody PostRequestPutDto entityPutDto
    ) {
        PostEntity updatedPostEntity = postProcessor.updatePostFromDto(id, entityPutDto);
        PostDto postDto = postEntityMapper.toPostDto(updatedPostEntity);
        log.info("Update post with id={}", id);

        return postDto;
    }


    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable Long id
    ) {
        postProcessor.delete(id);
        log.info("Delete post with id={}", id);
    }
    
    
}
