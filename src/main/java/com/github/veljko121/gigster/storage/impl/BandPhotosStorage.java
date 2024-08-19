package com.github.veljko121.gigster.storage.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.github.veljko121.gigster.core.storage.impl.ImageStorage;
import com.github.veljko121.gigster.storage.IBandPhotosStorage;

public class BandPhotosStorage extends ImageStorage implements IBandPhotosStorage {
    
    @Value("${simple-object-storage.images-directory.band-pictures}")
    private String bandPhotosDirectory;

    @Override
    public byte[] getByFilename(String bandPhotoFilename) throws IOException, InterruptedException {
        return super.getByFilename(getBandPhotoPath(bandPhotoFilename));
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
        var path = getBandPhotoPath(newFilename);
        var fullBandPhotoPath = super.upload(fileBytes, contentType, originalFilename, path);
        var bandPhotoPath = fullBandPhotoPath.replace(bandPhotosDirectory + '/', "");
        return bandPhotoPath;
    }

    private String getBandPhotoPath(String bandPhotoFilename) {
        return bandPhotosDirectory + '/' + bandPhotoFilename;
    }
    
}
