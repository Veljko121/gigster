package com.github.veljko121.gigster.core.storage.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.github.veljko121.gigster.core.storage.IImageStorage;


public abstract class ImageStorage extends SimpleObjectStorage implements IImageStorage {

    @Value("${simple-object-storage.images-directory:images}")
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
    public String upload(MultipartFile file, String newFilename) throws IOException, InterruptedException {
        return this.upload(file.getBytes(), file.getContentType(), file.getOriginalFilename(), newFilename);
    }

    @Override
    public String upload(byte[] fileBytes, String contentType, String originalFilename) throws IOException, InterruptedException {
        return this.upload(fileBytes, contentType, originalFilename, originalFilename);
    }
    
    @Override
    public String upload(byte[] fileBytes, String contentType, String originalFilename, String newFilename) throws IOException, InterruptedException {
        if (contentType == null) throw new IllegalArgumentException("Error with the attached file.");
        
        if (!contentType.contains("image")) throw new IllegalArgumentException("Attached file is not an image.");

        var fullImagePath = super.upload(fileBytes, contentType, originalFilename, getImagePath(newFilename));
        var imagePath = fullImagePath.replace(getImagesSubdirectory() + '/', "");
        
        return imagePath;
    }

    protected abstract String getImagesSubdirectory();

    @Override
    protected String getFilesSubdirectory() {
        return imagesDirectory;
    }

    private String getImagePath(String imageName) {
        return getImagesSubdirectory() + '/' + imageName;
    }

}
