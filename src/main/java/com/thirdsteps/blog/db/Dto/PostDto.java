package com.thirdsteps.blog.db.dto;

import com.thirdsteps.blog.statuses.PostStatus;

public record PostDto(
    Long id,
    Long authorId,
    String title,
    String fullText,
    PostStatus postStatus,
    Long views
) {

}
