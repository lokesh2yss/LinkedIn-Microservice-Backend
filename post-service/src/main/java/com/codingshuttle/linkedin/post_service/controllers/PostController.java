package com.codingshuttle.linkedin.post_service.controllers;

import com.codingshuttle.linkedin.post_service.dto.PostCreateRequestDto;
import com.codingshuttle.linkedin.post_service.dto.PostDto;
import com.codingshuttle.linkedin.post_service.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto,
                                              HttpServletRequest httpServletRequest) {
        PostDto createdPost = postService.createPost(postCreateRequestDto, 1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);

    }

    @GetMapping(path="/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId, HttpServletRequest request) {
        String userId = request.getHeader("X-User-Id");
        log.info("UserId passed in header is: "+userId);
        PostDto postDto = postService.getPostById(postId);

        return ResponseEntity.ok(postDto);
    }

    @GetMapping(path="/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostOfUser(@PathVariable Long userId) {
        List<PostDto> posts = postService.getAllPostOfUser(userId);
        return ResponseEntity.ok(posts);
    }
}
