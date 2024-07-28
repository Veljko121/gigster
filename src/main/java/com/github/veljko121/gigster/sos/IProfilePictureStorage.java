package com.github.veljko121.gigster.sos;

import com.github.veljko121.gigster.core.sos.IImageStorage;
import com.github.veljko121.gigster.model.RegisteredUser;

public interface IProfilePictureStorage extends IImageStorage {

    String getFullProfilePicturePath(RegisteredUser registeredUser);
    
}
