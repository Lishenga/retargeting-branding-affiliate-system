package com.retargeting_branding.utils;

import java.time.LocalDateTime;

import com.retargeting_branding.enums.Mode;
import com.retargeting_branding.enums.Status;

public interface DisplayAdverts {
    
    public Long getId();

    public String getTitle();

    public String getDescription();

    public String getMediaUrl();

    public Mode getAdvertMode();

    public Status getAdvertStatus();

    public String getExpirationDateTime();

    public Boolean getIsPublished();

    public Integer getMaxAdvertiserLimit();

    public String getRedirectLink();

    public String getDisplayWindowStartTime();

    public String getDisplayWindowStopTime();

    public String getName();

    public LocalDateTime getCreatedAt();

    public LocalDateTime getUpdatedAt();
}