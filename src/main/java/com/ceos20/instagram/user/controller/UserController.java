package com.ceos20.instagram.user.controller;

import com.ceos20.instagram.user.domain.User;
import com.ceos20.instagram.user.dto.JoinRequestDto;
import com.ceos20.instagram.user.dto.UserPageResponseDto;
import com.ceos20.instagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/accounts")
public class UserController {
    private final UserService userService;

    @GetMapping("/{nickname}")
    public ResponseEntity getUserPage(@PathVariable String nickname) {
        final UserPageResponseDto response = userService.getUserPage(nickname);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<User> createUser(@RequestBody JoinRequestDto joinRequestDto) {
        User newUser = userService.create(joinRequestDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/accounts/user/"+newUser.getId()).toUriString());

        return ResponseEntity.created(uri).body(newUser);
    }


}
