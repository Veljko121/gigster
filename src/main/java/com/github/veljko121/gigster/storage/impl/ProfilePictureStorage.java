package com.github.veljko121.gigster.storage.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.veljko121.gigster.core.storage.impl.ImageStorage;
import com.github.veljko121.gigster.storage.IProfilePictureStorage;

@Service
public class ProfilePictureStorage extends ImageStorage implements IProfilePictureStorage {
    
    @Value("${simple-object-storage.images-directory.profile-pictures}")
    private String profilePicturesDirectory;
    
    @Override
    public byte[] getByFilename(String profilePictureName) throws IOException, InterruptedException {
        return super.getByFilename(profilePicturesDirectory + '/' + profilePictureName);
    }

    @Override
    public String upload(MultipartFile file) throws IOException, InterruptedException {
        return this.upload(file, file.getOriginalFilename());
    }

    @Override
    public String upload(MultipartFile file, String newFilename) throws IOException, InterruptedException {
        return this.upload(file.getBytes(), file.getContentType(), file.getOriginalFilename(), newFilename);
    }

    @Override
    public String upload(byte[] fileBytes, String contentType, String originalFilename) throws IOException, InterruptedException {
        return this.upload(fileBytes, contentType, originalFilename, originalFilename);
    }

    @Override
    public String upload(byte[] fileBytes, String contentType, String originalFilename, String newFilename) throws IOException, InterruptedException {
        var path = getProfilePicturePath(newFilename);
        var fullProfilePicturePath = super.upload(fileBytes, contentType, originalFilename, path);
        var profilePicturePath = fullProfilePicturePath.replace(profilePicturesDirectory + '/', "");
        return profilePicturePath;
    }

    private String getProfilePicturePath(String profilePictureName) {
        return profilePicturesDirectory + '/' + profilePictureName;
    }
    
}