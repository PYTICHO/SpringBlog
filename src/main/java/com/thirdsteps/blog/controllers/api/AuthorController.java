package com.thirdsteps.blog.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.thirdsteps.blog.db.dto.AuthorDto;
import com.thirdsteps.blog.db.dto.AuthorRequestPostDto;
import com.thirdsteps.blog.db.entities.AuthorEntity;
import com.thirdsteps.blog.db.mappers.AuthorEntityMapper;
import com.thirdsteps.blog.services.AuthorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorEntityMapper authorMapper;

    @PostMapping()
    public AuthorDto create(
        @RequestBody AuthorRequestPostDto request
    ) {
        AuthorEntity authorEntity = authorService.create(request);
        AuthorDto authorDto = authorMapper.toAuthorDto(authorEntity);
        log.info("Creating author: {}", request);

        return authorDto;
    }  

    @GetMapping()
    public List<AuthorDto> getAllAuthors() {
        List<AuthorEntity> authorEntities = authorService.getAllAuthors();
        List<AuthorDto> authorDtos = authorMapper.toAuthorDtoList(authorEntities);
        return authorDtos;
    }
    
    
}
