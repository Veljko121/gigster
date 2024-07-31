package com.github.veljko121.gigster.core.storage.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.github.veljko121.gigster.core.storage.IImageStorage;


public class ImageStorage extends SimpleObjectStorage implements IImageStorage {

    @Value("${simple-object-storage.images-directory}")
    private String imagesDirectory;

    @Override
    public byte[] getByFilename(String imageName) throws IOException, InterruptedException {
        return super.getByFilename(getImagePath(imageName));
    }

    @Override
    public String upload(MultipartFile file) throws IOException, InterruptedException {
        return this.upload(file, file.getOriginalFilename());
    }

    @Override
    public String upload(MultipartFile file, String imageName) throws IOException, InterruptedException {
        var contentType = file.getContentType();

        if (contentType == null) throw new IllegalArgumentException("Error with the attached file.");
        
        if (!contentType.contains("image")) throw new IllegalArgumentException("Attached file is not an image.");

        var path = getImagePath(imageName);
        var fullImagePath = super.upload(file, path);
        var imagePath = fullImagePath.replace(imagesDirectory + '/', "");
        
        return imagePath;
    }

    private String getImagePath(String imageName) {
        return imagesDirectory + '/' + imageName;
    }

}
