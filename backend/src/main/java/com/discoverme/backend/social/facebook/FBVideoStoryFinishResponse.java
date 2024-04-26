package com.discoverme.backend.social.facebook;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FBVideoStoryFinishResponse {
    private boolean success;
    private Long post_id;
}
