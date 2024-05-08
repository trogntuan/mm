package com.member.management.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MemberDto {
    @NotEmpty(message = "{common.error.required}")
    private String id;
    @NotEmpty(message = "{common.error.required}")
    private String name;
    private String department;
    private String major;
    private String phone;
    @NotEmpty(message = "{common.error.required}")
    private String password;
}
