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
    private Long compId;
    private String profileImageUrl;
    private String memberName;
    private String email;
    private String phoneNumber;
    private String gender;
    private String hobby;
    private String address;
    private String deptName;
    private String task;
    private String position;
    private List<String> techSkills;
    private String mbti;
    private List<String> links = new ArrayList<String>();
    private String description;
    private String memberCustomUrl;

    @Builder
    public ProfileDto(Long compId, String profileImageUrl, String memberName, String email, String phoneNumber, String gender,String hobby, String address, String deptName, String task, String position, List<String> techSkills, String mbti, List<String> links, String description, String memberCustomUrl) {
        this.compId = compId;
        this.profileImageUrl = profileImageUrl;
        this.memberName = memberName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.hobby = hobby;
        this.address = address;
        this.deptName = deptName;
        this.task = task;
        this.position = position;
        this.techSkills = techSkills;
        this.mbti = mbti;
        this.links = links;
        this.description = description;
        this.memberCustomUrl = memberCustomUrl;
    }
}
