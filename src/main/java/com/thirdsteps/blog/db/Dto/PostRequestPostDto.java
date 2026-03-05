package com.thirdsteps.blog.db.Dto;

public record PostRequestPostDto(
    String author,
    String title,
    String fullText
) {

}
