package com.springwithAWS.book.springboot.service.posts;

import com.springwithAWS.book.springboot.domain.posts.Posts;
import com.springwithAWS.book.springboot.domain.posts.PostsRepository;
import com.springwithAWS.book.springboot.web.dto.PostResponseDto;
import com.springwithAWS.book.springboot.web.dto.PostSaveRequestDto;
import com.springwithAWS.book.springboot.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("해당 게시글이 없습니다")));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("No gesigeul")));
        return new PostResponseDto(entity);
    }
}
