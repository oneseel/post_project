package com.sparta.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostRequestDto {
    private String title;
    private String username;
    private String pw;
    private String contents;
}
