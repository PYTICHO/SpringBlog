package com.thirdsteps.blog.db.entities.Dto;

import com.thirdsteps.blog.db.statuses.PostStatus;

public record PostDto(
    Long id,
    String author,
    String title,
    String fullText,
    PostStatus postStatus,
    Integer views
) {

}
