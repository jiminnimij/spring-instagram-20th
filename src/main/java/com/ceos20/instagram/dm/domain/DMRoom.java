package com.ceos20.instagram.dm.domain;

import com.ceos20.instagram.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DMRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    // 대화에 참여하는 유저1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userID1;

    // 대화에 참여하는 유저2
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userID2;

    @Column(nullable = false)
    private Long restChatCount= 0L;
}
