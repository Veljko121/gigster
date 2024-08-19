package com.github.veljko121.gigster.storage.impl;

import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.storage.impl.ImageStorage;
import com.github.veljko121.gigster.storage.IBandPhotosStorage;

@Service
public class BandPhotosStorage extends ImageStorage implements IBandPhotosStorage {

    @Override
    protected String getImagesSubdirectory() {
        return "bands";
    }
    
}
