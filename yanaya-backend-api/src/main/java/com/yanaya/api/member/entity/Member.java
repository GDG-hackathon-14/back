package com.yanaya.api.member.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private String email;
    private String password;

    public Member() {
    }

    @Builder
    public Member(Long memberId, String email, String password) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
    }
}
