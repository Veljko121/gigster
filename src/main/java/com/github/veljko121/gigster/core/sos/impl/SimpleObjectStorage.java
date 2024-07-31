package com.github.veljko121.gigster.core.sos.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.github.veljko121.gigster.core.sos.ISimpleObjectStorage;

public abstract class SimpleObjectStorage implements ISimpleObjectStorage {

    @Value("${simple-object-storage.host}")
    private String simpleObjectStorageHost;

    @Value("${simple-object-storage.port}")
    private String simpleObjectStoragePort;

    private HttpClient httpClient = HttpClient.newBuilder().build();

    protected String getSimpleObjectStorageUrl() {
        return "http://" + simpleObjectStorageHost + ':' + simpleObjectStoragePort;
    }

    @Override
    public byte[] getByFilename(String filename) throws IOException, InterruptedException {
        var path = getSimpleObjectStorageUrl() + '/' + filename;
        var request = HttpRequest.newBuilder(URI.create(path)).GET().build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
        return response.body();
    }

    @Override
    public String upload(MultipartFile file) throws IOException, InterruptedException {
        return upload(file, file.getOriginalFilename());
    }

    @Override
    public String upload(MultipartFile file, String filename) throws IOException, InterruptedException {
        var fileExtension = extractFileExtension(file);

        var filePath = filename + fileExtension;
        var requestPath = getSimpleObjectStorageUrl() + '/' + filePath;
        var boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
        outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + filename + "\"\r\n").getBytes(StandardCharsets.UTF_8));
        outputStream.write(("Content-Type: " + file.getContentType() + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
        outputStream.write(file.getBytes());
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));

        var request = HttpRequest.newBuilder(URI.create(requestPath))
                .POST(HttpRequest.BodyPublishers.ofByteArray(outputStream.toByteArray()))
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to upload file: " + response.body());
        }

        return filePath;
    }

    private String extractFileExtension(MultipartFile file) {
        return extractFileExtension(file.getOriginalFilename());
    }

    private String extractFileExtension(String filename) {
        var fileExtensionTokens = filename.split("\\.");
        var fileExtension = '.' + fileExtensionTokens[fileExtensionTokens.length - 1];
        return fileExtension;
    }

}
