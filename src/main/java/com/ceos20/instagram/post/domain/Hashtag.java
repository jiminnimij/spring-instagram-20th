package com.ceos20.instagram.post.domain;

import jakarta.persistence.*;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    // 게시글 해시태그
    @Column(nullable = false)
    private String tag;
}
