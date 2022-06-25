package com.yanaya.api.profile.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "mamber_name")
    private String memberName;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Boolean gender;

    @Column(name = "dept_name")
    private String deptName;

    private String address;

    private String task;

    private String position;
    private String mbti;

    @ElementCollection
    private List<String> links = new ArrayList<String>();
    private String description;

    public Profile() {
    }

    @Builder
    public Profile(Long profileId, String profileImageUrl, String memberName, String email, String phoneNumber, Boolean gender, String deptName, String address, String task, String position, String mbti, List<String> links, String description) {
        this.profileId = profileId;
        this.profileImageUrl = profileImageUrl;
        this.memberName = memberName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.deptName = deptName;
        this.address = address;
        this.task = task;
        this.position = position;
        this.mbti = mbti;
        this.links = links;
        this.description = description;
    }
}