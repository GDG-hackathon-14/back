package com.yanaya.api.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginDto {
    private Long memberId;
    private String memberCustomUrl;

    @Builder
    public MemberLoginDto(Long memberId, String memberCustomUrl) {
        this.memberId = memberId;
        this.memberCustomUrl = memberCustomUrl;
    }
}
