package com.github.veljko121.gigster.config;

import org.slf4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.github.veljko121.gigster.repository.GigListingRepository;
import com.github.veljko121.gigster.service.IGigsterSearchEngineService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ElasticsearchMigrations implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger logger;
    private final GigListingRepository gigListingRepository;
    private final IGigsterSearchEngineService gigsterSearchEngineService;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        logger.info("Starting Elasticsearch migrations.");
        try {
            gigsterSearchEngineService.deleteAllGigListings();
            logger.info("Deleted all gig-listings from Elasticsearch.");
            var gigListings = gigListingRepository.findAll();
            for (var gigListing : gigListings) {
                gigsterSearchEngineService.createGigListing(gigListing);
            }
            logger.info("Migrated all gig-listings to Elasticsearch.");
            logger.info("Elasticsearch migrations successful.");
        } catch (Exception e) {
            logger.error("Error when migrating data to Elasticsearch.");
        }
    }

}
