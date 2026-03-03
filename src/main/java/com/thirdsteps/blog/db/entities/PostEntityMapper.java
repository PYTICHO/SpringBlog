package com.thirdsteps.blog.db.entities;

import java.util.List;

import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PostEntityMapper {
    PostEntity toPostEntity(PostRequestDto requestDto);

    List<PostDto> toPostDtoList(List<PostEntity> entities);

    PostDto toPostDto(PostEntity postEntity);
}
