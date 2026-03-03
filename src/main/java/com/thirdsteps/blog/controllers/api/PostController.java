package com.thirdsteps.blog.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.thirdsteps.blog.db.PostProcessor;
import com.thirdsteps.blog.db.entities.PostDto;
import com.thirdsteps.blog.db.entities.PostEntity;
import com.thirdsteps.blog.db.entities.PostEntityMapper;
import com.thirdsteps.blog.db.entities.PostRequestDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostProcessor postProcessor;
    private final PostEntityMapper postEntityMapper;

    @PostMapping()
    public PostDto create(
        @RequestBody PostRequestDto request
    ) {
        log.info("Creating post: {}", request);
        PostEntity entity = postProcessor.create(request);
        return postEntityMapper.toPostDto(entity);
    }

    @GetMapping()
    public List<PostDto> getAllPosts() {
        log.info("Return all posts");
        List<PostDto> posts = postProcessor.getAll();
        return posts;
    }
    
    
}
