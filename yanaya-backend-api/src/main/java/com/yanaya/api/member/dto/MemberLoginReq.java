package com.yanaya.api.member.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginReq {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Builder
    public MemberLoginReq(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
