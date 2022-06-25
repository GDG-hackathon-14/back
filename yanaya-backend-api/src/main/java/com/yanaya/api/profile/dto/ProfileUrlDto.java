package com.yanaya.api.profile.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileUrlDto {

    private String memberCustomUrl;

    @Builder
    public ProfileUrlDto(String memberCustomUrl) {
        this.memberCustomUrl = memberCustomUrl;
    }
}
