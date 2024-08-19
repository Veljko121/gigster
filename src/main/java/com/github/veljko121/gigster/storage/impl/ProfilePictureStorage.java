package com.github.veljko121.gigster.storage.impl;

import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.storage.impl.ImageStorage;
import com.github.veljko121.gigster.storage.IProfilePictureStorage;

@Service
public class ProfilePictureStorage extends ImageStorage implements IProfilePictureStorage {

    @Override
    protected String getImagesSubdirectory() {
        return "profile-pictures";
    }
    
}
