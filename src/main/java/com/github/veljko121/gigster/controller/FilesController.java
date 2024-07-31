package com.github.veljko121.gigster.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.veljko121.gigster.storage.IProfilePictureStorage;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FilesController {

    private final IProfilePictureStorage profilePictureStorage;

    @GetMapping("/images/profile-pictures/{profilePictureName}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable String profilePictureName) throws IOException, InterruptedException {
        var profilePicture = profilePictureStorage.getByFilename(profilePictureName);
        return ResponseEntity.ok(profilePicture);
    }
    
    
}
