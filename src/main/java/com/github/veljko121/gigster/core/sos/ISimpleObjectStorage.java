package com.github.veljko121.gigster.core.sos;

import java.io.IOException;

public interface ISimpleObjectStorage {

    byte[] getByFilename(String filename) throws IOException, InterruptedException;

}
