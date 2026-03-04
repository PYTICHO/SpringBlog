package com.thirdsteps.blog.db.entities;

import lombok.*;

import com.thirdsteps.blog.db.statuses.PostStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="blogs")
public class PostEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "author", nullable = false)
    private String author;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank
    @Column(name = "full_text", nullable = false)
    private String fullText;

    @NotBlank
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "post_status", nullable = false)
    private PostStatus postStatus;

    @Column(name = "views")
    private Integer views;
}
