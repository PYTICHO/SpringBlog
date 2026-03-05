package com.thirdsteps.blog.db.entities;

import java.util.List;

import com.thirdsteps.blog.db.Dto.PostDto;
import com.thirdsteps.blog.db.Dto.PostRequestPostDto;
import com.thirdsteps.blog.db.Dto.PostRequestPutDto;

import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PostEntityMapper {
    PostEntity toPostEntity(PostRequestPostDto requestDto);

    List<PostDto> toPostDtoList(List<PostEntity> entities);

    PostDto toPostDto(PostEntity postEntity);


    // update entity from RequestPutDto
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostEntityFromRequestPutDto(
        PostRequestPutDto entityDto,
        @MappingTarget PostEntity entity
    );
}
