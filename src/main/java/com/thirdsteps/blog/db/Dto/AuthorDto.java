package com.thirdsteps.blog.db.dto;

import java.util.Set;

public record AuthorDto(
    Long id,
    String name,
    Set<PostDto> posts
) {
}
