package com.github.veljko121.gigster.dto;

import lombok.Data;

@Data
public class ChangePasswordRequestDTO {

    private String oldPassword;
    
    private String newPassword;
    
}
