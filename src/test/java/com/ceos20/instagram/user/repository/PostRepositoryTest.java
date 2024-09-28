package com.ceos20.instagram.user.repository;

import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.post.repository.PostRepository;
import com.ceos20.instagram.user.repository.UserRepository;
import com.ceos20.instagram.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostRepositoryTest {
    @Test
    @DisplayName("user가 생성한 포스트 전부 조회")
    public void findPostsByUser() {
        // When
        User user1 = User.builder()
                .nickname("user1")
                .password("1234")
                .build();
        UserRepository userRepository = null;
        userRepository.save(user1);

        Post post1 = Post.builder()
                .writer(user1)
                .content("1234")
                .build();
        Post post2 = Post.builder()
                .writer(user1)
                .content("1234")
                .build();

        PostRepository postRepository = null;
        postRepository.save(post1);

        List<Post> posts = postRepository.findPostByWriter(user1.getId());

        assertEquals(2, posts.size());
    }
}
