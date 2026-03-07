package com.thirdsteps.blog.db.mappers;

import java.util.List;

import com.thirdsteps.blog.db.dto.*;
import com.thirdsteps.blog.db.entities.PostEntity;

import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PostEntityMapper {

    @Mapping(target = "author", ignore = true)
    PostEntity toPostEntity(PostRequestPostDto requestDto);

    List<PostDto> toPostDtoList(List<PostEntity> entities);

    @Mapping(target = "authorId", source = "author.id")
    PostDto toPostDto(PostEntity postEntity);

    // update entity from RequestPutDto
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostEntityFromRequestPutDto(
        PostRequestPutDto entityDto,
        @MappingTarget PostEntity entity
    );
}
