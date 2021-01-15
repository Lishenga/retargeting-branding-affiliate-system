package com.retargeting_branding.utils;

import java.time.LocalDateTime;

public interface GetTags {
    
    public Long getId();

    public String getName();

    public LocalDateTime getUpdatedAt();

    public LocalDateTime getCreatedAt();
}