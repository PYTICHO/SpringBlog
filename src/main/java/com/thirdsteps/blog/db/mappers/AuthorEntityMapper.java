package com.thirdsteps.blog.db.mappers;

import java.util.List;

import com.thirdsteps.blog.db.dto.AuthorDto;
import com.thirdsteps.blog.db.dto.AuthorRequestPostDto;
import com.thirdsteps.blog.db.entities.AuthorEntity;

import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = PostEntityMapper.class   //если встретишь маппинг PostEntity -> PostDto, используй уже готовый mapper PostEntityMapper
)
public interface AuthorEntityMapper {
    
    AuthorEntity toAuthorEntity(AuthorRequestPostDto requestDto);

    List<AuthorDto> toAuthorDtoList(List<AuthorEntity> entities);

    AuthorDto toAuthorDto(AuthorEntity entity);
}
