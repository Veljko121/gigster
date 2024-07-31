package com.github.veljko121.gigster.core.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ISimpleObjectStorage {

    byte[] getByFilename(String filename) throws IOException, InterruptedException;

    String upload(MultipartFile file) throws IOException, InterruptedException;

    String upload(MultipartFile file, String filename) throws IOException, InterruptedException;

}
