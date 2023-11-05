package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 글 작성하기
    public PostResponseDto createPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // DB 저장
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    // 선택한 글 조회하기
    public PostResponseDto getPost(Long id) {
        // 해당 글이 DB에 존재하는지 확인
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글이 존재하지 않습니다.")
        );
        // post 조회
        return new PostResponseDto(post);
    }

    // 글 목록 조회하기
    public List<PostResponseDto> getPosts() {
        // DB 조회
        return postRepository.findAllByOrderByModifiedAtDesc().
                stream().map(PostResponseDto::new).toList();

    }

    // 글 수정하기
    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {
        // 해당 글이 DB에 존재하는지 확인
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글이 존재하지 않습니다.")
        );

        // post 내용 수정
        post.update(requestDto);

        return id;
    }

    // 글 삭제하기
    public Long deletePost(Long id) {
        // 해당 글이 DB에 존재하는지 확인
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글이 존재하지 않습니다.")
        );

        // post 내용 삭제
        postRepository.delete(post);

        return id;
    }
}
