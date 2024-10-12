package com.codingshuttle.linkedin.post_service.services;

import com.codingshuttle.linkedin.post_service.dto.PostDto;
import com.codingshuttle.linkedin.post_service.entities.Post;
import com.codingshuttle.linkedin.post_service.entities.PostLike;
import com.codingshuttle.linkedin.post_service.exceptions.BadRequestException;
import com.codingshuttle.linkedin.post_service.exceptions.ResourceNotFoundException;
import com.codingshuttle.linkedin.post_service.repositories.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostService postService;

    public void likePost(Long postId, Long userId) {
        log.info("Attempting to like the post with id: {}", postId);
        boolean exists = postService.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not found with id: "+postId);
        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(alreadyLiked) throw new BadRequestException("Cannot like the same post again");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);

        postLikeRepository.save(postLike);

        log.info("Post with id: {} liked successfully", postId);
    }

    @Transactional
    public void unlikePost(Long postId, long userId) {
        log.info("Attempting to unlike the post with id: {}", postId);
        //PostDto postDto = postService.findPostById(postId);
        boolean exists = postService.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not found with id: "+postId);
        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(!alreadyLiked) throw new BadRequestException("Cannot unlike a post that is not liked yet");

        postLikeRepository.deleteByUserIdAndPostId(userId, postId);

        log.info("Post with id: {} unliked successfully", postId);
    }
}
