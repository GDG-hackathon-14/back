package com.yanaya.api.company.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id")
    private Long compId;

    @Column(name = "comp_name")
    private String compName;

    public Company() {
    }

    @Builder
    public Company(Long compId, String compName) {
        this.compId = compId;
        this.compName = compName;
    }
}
