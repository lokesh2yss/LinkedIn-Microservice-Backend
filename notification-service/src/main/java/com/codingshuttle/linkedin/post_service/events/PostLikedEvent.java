package com.codingshuttle.linkedin.post_service.events;

import lombok.Builder;
import lombok.Data;

@Data
public class PostLikedEvent {
    private Long postId;
    private Long creatorId;
    private Long likedByUserId;
}
