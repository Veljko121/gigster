package com.github.veljko121.gigster.core.sos.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import com.github.veljko121.gigster.core.sos.IImageStorage;


public class ImageStorage extends SimpleObjectStorage implements IImageStorage {

    @Value("${simple-object-storage.images-directory}")
    private String imagesDirectory;

    @Override
    public String getFullImagePath(String imageName) {
        return getSimpleObjectStorageUrl() + '/' + imagesDirectory + '/' + imageName;
    }

    @Override
    public byte[] getByFilename(String imageName) throws IOException, InterruptedException {
        return super.getByFilename(imagesDirectory + '/' + imageName);
    }

}
