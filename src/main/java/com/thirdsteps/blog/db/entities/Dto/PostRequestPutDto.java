package com.thirdsteps.blog.db.entities.Dto;

import com.thirdsteps.blog.db.statuses.PostStatus;

public record PostRequestPutDto(
    String author,
    String title,
    String fullText,
    PostStatus postStatus
) {

}
