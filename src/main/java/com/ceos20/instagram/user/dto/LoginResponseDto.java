package com.ceos20.instagram.user.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class LoginResponseDto {
    private int status;
    private String message;
}
