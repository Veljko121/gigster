package com.github.veljko121.gigster.core.sos.impl;

import org.springframework.beans.factory.annotation.Value;

import com.github.veljko121.gigster.core.sos.ISimpleObjectStorage;

public abstract class SimpleObjectStorage implements ISimpleObjectStorage {

    @Value("${simple-object-storage.host}")
    private String simpleObjectStorageHost;

    @Value("${simple-object-storage.port}")
    private String simpleObjectStoragePort;

    protected String getSimpleObjectStorageUrl() {
        return "http://" + simpleObjectStorageHost + ':' + simpleObjectStoragePort;
    }

}
