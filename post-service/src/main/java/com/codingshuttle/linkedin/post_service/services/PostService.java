package com.codingshuttle.linkedin.post_service.services;

import com.codingshuttle.linkedin.post_service.auth.UserContextHolder;
import com.codingshuttle.linkedin.post_service.clients.ConnectionClient;
import com.codingshuttle.linkedin.post_service.dto.PersonDto;
import com.codingshuttle.linkedin.post_service.dto.PostCreateRequestDto;
import com.codingshuttle.linkedin.post_service.dto.PostDto;
import com.codingshuttle.linkedin.post_service.entities.Post;
import com.codingshuttle.linkedin.post_service.exceptions.ResourceNotFoundException;
import com.codingshuttle.linkedin.post_service.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final ConnectionClient connectionClient;
    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        Long userId = UserContextHolder.getCurrentUserId();
        List<PersonDto> firstConnections = connectionClient.getFirstConnections();

        // TODO send notifications to all these connections

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: "+postId));

        return modelMapper.map(post, PostDto.class);
    }

    public boolean existsById(Long postId) {
        return postRepository.existsById(postId);
    }

    public List<PostDto> getAllPostOfUser(Long userId) {
        return postRepository.findByUserId(userId)
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post not found with id: "+postId));

        return modelMapper.map(post, PostDto.class);
    }
}
