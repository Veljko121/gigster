package com.github.veljko121.gigster.core.sos.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;

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

}
