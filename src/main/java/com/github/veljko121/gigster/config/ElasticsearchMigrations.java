package com.github.veljko121.gigster.config;

import org.slf4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.github.veljko121.gigster.repository.GigListingRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ElasticsearchMigrations implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger logger;
    private final GigListingRepository gigListingRepository;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        logger.info("Script can be executed.");
        var gigListings = gigListingRepository.findAll();
    }
    
}
