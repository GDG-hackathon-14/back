package com.yanaya.api.profile.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileReq {

    @NotNull
    private Long compId;
    private String profileImageUrl;
    @NotBlank
    private String memberName;
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String gender;

    private String hobby;
    private String address;
    @NotBlank
    private String deptName;
    @NotBlank
    private String task;
    @NotBlank
    private String position;

    private List<String> techSkills;

    private String mbti;
    private List<String> links = new ArrayList<String>();
    @Size(min=0,max=100)
    private String description;

    @Builder
    public ProfileReq(String profileImageUrl, String memberName, String email, String phoneNumber, String gender,String hobby, String deptName, String address, String task, String position, List<String> techSkills, String mbti, List<String> links, String description) {
        this.profileImageUrl = profileImageUrl;
        this.memberName = memberName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.hobby = hobby;
        this.deptName = deptName;
        this.address = address;
        this.task = task;
        this.position = position;
        this.techSkills = techSkills;
        this.mbti = mbti;
        this.links = links;
        this.description = description;
    }
}
