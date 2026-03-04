package com.thirdsteps.blog.db.entities.Dto;

public record PostRequestPostDto(
    String author,
    String title,
    String fullText
) {

}
