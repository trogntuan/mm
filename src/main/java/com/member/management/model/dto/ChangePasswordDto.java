package com.member.management.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChangePasswordDto {
    @NotEmpty(message = "{common.error.required}")
    private String oldPassword;
    @NotEmpty(message = "{common.error.required}")
    private String newPassword;
}
