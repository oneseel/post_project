package com.sparta.post.controller;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 글 작성
    @PostMapping ("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    // 글 조회
    @GetMapping ("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 글 목록 조회
    @GetMapping("/post")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 글 수정
    @PutMapping ("/post/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 글 삭제
    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
