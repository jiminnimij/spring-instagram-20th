package com.ceos20.instagram.post.domain;

import com.ceos20.instagram.user.domain.User;
import jakarta.persistence.*;

@Entity
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_like_id")
    private Long id;

    // 좋아요한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User userID;

    // 좋아한 게시글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    private Post postID;
}
