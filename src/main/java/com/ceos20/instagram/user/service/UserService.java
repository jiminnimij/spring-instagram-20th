package com.ceos20.instagram.user.service;

import com.ceos20.instagram.global.exception.BadRequestException;
import com.ceos20.instagram.global.exception.ExceptionCode;
import com.ceos20.instagram.global.exception.NotFoundException;
import com.ceos20.instagram.jwt.CustomUserDetails;
import com.ceos20.instagram.jwt.JwtUtil;
import com.ceos20.instagram.jwt.RedisService;
import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.post.repository.PostRepository;
import com.ceos20.instagram.user.domain.User;
import com.ceos20.instagram.user.dto.JoinRequestDto;
import com.ceos20.instagram.user.dto.UserPageResponseDto;
import com.ceos20.instagram.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final PostRepository postRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    private final RedisService redisService;
//
//    @Transactional
//    public void create(UserCreateDto userCreateDto) {
//        if(userRepository.existUserLoginNickname(userCreateDto.getNickname())) {
//            throw new IllegalStateException("already exist");
//        }
//
//        User user = userCreateDto.toEntity();
//        userRepository.save(user);
//    }
//
//    public User getUser(Long userId) {
//        return userRepository.findUserById(userId)
//                .orElseThrow(IllegalStateException::new);
//    }

    public UserPageResponseDto getUserPage(String nickname){

        final User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

        final List<Post> posts = postRepository.findByWriter_Id(user.getId());
        return UserPageResponseDto.of(user, posts);
    }

    @Transactional
    public User create(JoinRequestDto joinRequestDto){
        String nickname = joinRequestDto.getNickname();
        Boolean isExist = userRepository.existsByNickname(nickname);
        if(isExist) throw new BadRequestException(ExceptionCode.ALREADY_EXIST_NICKNAME);

        String encPassword = bCryptPasswordEncoder.encode(joinRequestDto.getPassword());
        User user = joinRequestDto.toEntity(joinRequestDto, encPassword);

        return userRepository.save(user);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByNickname(username)
                .orElseThrow(() -> new BadRequestException(ExceptionCode.NOT_FOUND_USER));

        if(findUser == null){
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        }
        return new CustomUserDetails(findUser);
    }

    public String reissue(String refreshToken) {
        String nickname = jwtUtil.getNickname(refreshToken);

        Optional<String> optionalToken = redisService.find(nickname);
        if (optionalToken.isPresent()) {
            if (!optionalToken.get().equals(refreshToken)) {
                throw new RuntimeException("유효하지 않은 refreshToken 입니다.");
            }

            User user = userRepository.findByNickname(nickname)
                    .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));

            return jwtUtil.generateAccessToken(nickname);
        } else {
            throw new RuntimeException("존재하지 않는 토큰입니다.");
        }
    }
}
