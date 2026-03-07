package com.thirdsteps.blog.db.dto;

import com.thirdsteps.blog.statuses.PostStatus;

public record PostRequestPutDto(
    String title,
    String fullText,
    PostStatus postStatus
) {

}
