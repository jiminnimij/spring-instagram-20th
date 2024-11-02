package com.ceos20.instagram.user.controller;

import com.ceos20.instagram.user.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/follows")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{nickname}")
    public ResponseEntity createFollow(@PathVariable String nickname, @RequestParam("user") String user) {
        followService.createFollow(followCreateDto, nickname, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{nickname}/followings")
    public ResponseEntity getFollowings(@PathVariable String nickname) {
        final List<FollowingResponseDto> response = followService.getFollowing(nickname);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{nickname}/followers")
    public ResponseEntity getFollowers(@PathVariable String nickname) {
        final List<FollowerResponseDto> response = followService.getFollower(nickname);
        return ResponseEntity.ok().body(response);
    }
}
