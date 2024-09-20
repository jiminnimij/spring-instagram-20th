package com.ceos20.instagram.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    // 팔로우 한 시간
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 친한 치구 여부
    private Boolean isBestFriend = false;

    // 팔로우 한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userID;

    // 팔로우 당한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User followingID;


}
