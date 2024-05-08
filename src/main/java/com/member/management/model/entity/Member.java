package com.member.management.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Data
@Entity
@Table(name = "thanhvien")
public class Member {

    @Id
    @Column(name = "MaTV", nullable = false, length = 10)
    private String id;

    @Column(name = "HoTen", nullable = false, length = 100)
    @Nationalized
    private String name;

    @Column(name = "Khoa", length = 100)
    @Nationalized
    private String department;

    @Column(name = "Nganh", length = 100)
    @Nationalized
    private String major;

    @Column(name = "SDT", length = 10)
    private String phone;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member")
    private List<UsageInformation> usageInformation;

    @OneToMany(mappedBy = "member")
    private List<HandlingViolation> handlingViolation;

}
