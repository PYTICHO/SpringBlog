package com.thirdsteps.blog.db.Dto;

import com.thirdsteps.blog.statuses.PostStatus;

public record PostRequestPutDto(
    String author,
    String title,
    String fullText,
    PostStatus postStatus
) {

}
