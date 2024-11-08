package com.ceos20.instagram.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import com.ceos20.instagram.user.domain.User;

@Builder
@Getter
@ToString
public class JoinRequestDto {
    private String nickname;
    private String password;

    public User toEntity(JoinRequestDto joinRequestDto, String encPassword) {
        return User.builder()
                .nickname(joinRequestDto.getNickname())
                .password(encPassword)
                .build();
    }
}
