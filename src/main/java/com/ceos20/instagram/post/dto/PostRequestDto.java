package com.ceos20.instagram.post.dto;

import com.ceos20.instagram.post.domain.Post;
import com.ceos20.instagram.post.domain.PostImage;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PostRequestDto {
    private String content;
    private String userId;
    private LocalDateTime createdAt;
    private List<MultipartFile> images;

//    public Post toEntity() {
//        return Post.builder()
//                .content(this.content)
//                .writer(this.userId)
//                .createdAt(this.createdAt)
//                .
//    }

}