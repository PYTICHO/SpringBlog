package com.thirdsteps.blog.db.entities;

public record PostRequestDto(
    String author,
    String title,
    String fullText
) {

}
