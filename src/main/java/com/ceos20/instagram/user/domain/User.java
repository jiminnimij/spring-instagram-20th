package com.ceos20.instagram.user.domain;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    private String email;

    private String phone;

    @Column(length = 150, nullable = true)
    private String introduce;

    private String gender;

    private String profileImage;

    private String name;

    private Long followingCount;

    private Long followedCount;

    private LocalDateTime registedAt;

    private LocalDateTime birth;



}
