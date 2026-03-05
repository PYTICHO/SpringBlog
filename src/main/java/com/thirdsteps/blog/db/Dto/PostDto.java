package com.thirdsteps.blog.db.Dto;

import com.thirdsteps.blog.statuses.PostStatus;

public record PostDto(
    Long id,
    String author,
    String title,
    String fullText,
    PostStatus postStatus,
    Long views
) {

}
