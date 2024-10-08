package com.ceos20.instagram.post.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long id;

    // 파일 경로
    @NotNull
    private String path;

    // 이미지 이름
    private String imageName;

    // 이미지가 사용된 게시글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    @NotNull
    private Post post;
}
