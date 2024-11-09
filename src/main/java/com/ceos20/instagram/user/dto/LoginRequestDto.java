package com.ceos20.instagram.user.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class LoginRequestDto {
    String nickname;
    String password;
}
