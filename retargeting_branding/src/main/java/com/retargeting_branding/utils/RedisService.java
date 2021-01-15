package com.retargeting_branding.utils;

import java.net.MalformedURLException;

import com.retargeting_branding.exceptions.general.GeneralExceptionHandler;
import com.retargeting_branding.requests.adevents.CreateAdEventsRequest;
import com.retargeting_branding.requests.impression.CreateImpressionRequest;
import com.retargeting_branding.services.AdEventsService;
import com.retargeting_branding.services.ImpressionService;

import org.springframework.beans.factory.annotation.Autowired;

public class RedisService {

    @Autowired
    private ImpressionService impressionService;

    @Autowired
    private AdEventsService adEventsService;

    public void createImpression(CreateImpressionRequest createImpressionRequest)
            throws GeneralExceptionHandler, MalformedURLException {
        impressionService.createImpression(createImpressionRequest);
    }

    public void createAdEvents(CreateAdEventsRequest createAdEventsRequest) throws GeneralExceptionHandler {
        adEventsService.createAdEvent(createAdEventsRequest);
    }
}