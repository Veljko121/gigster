package com.github.veljko121.gigster.sos.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.veljko121.gigster.core.sos.impl.ImageStorage;
import com.github.veljko121.gigster.model.RegisteredUser;
import com.github.veljko121.gigster.sos.IProfilePictureStorage;

@Service
public class ProfilePictureStorage extends ImageStorage implements IProfilePictureStorage {
    
    @Value("${simple-object-storage.images-directory.profile-pictures}")
    private String profilePicturesDirectory;
    
    @Override
    public String getFullProfilePicturePath(RegisteredUser registeredUser) {
        return getFullImagePath(getProfilePicturePath(registeredUser));
    }

    private String getProfilePicturePath(RegisteredUser registeredUser) {
        return profilePicturesDirectory + "/" + registeredUser.getProfilePicturePath();
    }
    
}
