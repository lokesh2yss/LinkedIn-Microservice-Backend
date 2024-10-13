package com.codingshuttle.linkedin.post_service.controllers;

import com.codingshuttle.linkedin.post_service.services.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/{postId}/likes")
@RequiredArgsConstructor
public class LikeController {
    private final PostLikeService postLikeService;

    @PostMapping
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        postLikeService.likePost(postId, 1L);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId) {
        postLikeService.unlikePost(postId, 1L);
        return ResponseEntity.ok().build();
    }
}
