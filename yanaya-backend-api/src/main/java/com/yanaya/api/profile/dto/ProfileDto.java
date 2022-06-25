package com.yanaya.api.profile.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileDto {
    private Long profileId;
    private String compName;
    private String profileImageUrl;
    private String memberName;
    private String email;
    private String phoneNumber;
    private Boolean gender;
    private String address;
    private String deptName;
    private String task;
    private String position;
    private String mbti;
    private List<String> links = new ArrayList<String>();
    private String description;
    private String memberCustomUrl;

    @Builder
    public ProfileDto(Long profileId, String compName, String profileImageUrl, String memberName, String email, String phoneNumber, Boolean gender, String address, String deptName, String task, String position, String mbti, List<String> links, String description, String memberCustomUrl) {
        this.profileId = profileId;
        this.compName = compName;
        this.profileImageUrl = profileImageUrl;
        this.memberName = memberName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.deptName = deptName;
        this.task = task;
        this.position = position;
        this.mbti = mbti;
        this.links = links;
        this.description = description;
        this.memberCustomUrl = memberCustomUrl;
    }
}
