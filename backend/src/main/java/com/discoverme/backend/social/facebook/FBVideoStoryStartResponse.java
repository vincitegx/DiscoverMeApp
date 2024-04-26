package com.discoverme.backend.social.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.restfb.Facebook;
import com.restfb.types.AbstractFacebookType;
import com.restfb.util.ReflectionUtils;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

public class FBVideoStoryStartResponse extends AbstractFacebookType {
    private static final long serialVersionUID = 1L;
    @Facebook("video_id")
    private String videoId;
    @Facebook("upload_url")
    private String uploadUrl;

    public FBVideoStoryStartResponse() {
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }
}
