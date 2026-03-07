package com.thirdsteps.blog.db.dto;

public record PostRequestPostDto(
    Long authorId,
    String title,
    String fullText
) {

}
