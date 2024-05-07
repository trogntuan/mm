package com.member.management.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "thongtinsd")
public class UsageInformation {

    @Id
    @Column(name = "MaTT", nullable = false, length = 10)
    private String id;

    @ManyToOne
    @JoinColumn(name = "MaTV", referencedColumnName = "MaTV", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "MaTB", referencedColumnName = "MaTB")
    private Device device;

    @Column(name = "TGVao")
    private LocalDateTime checkIn;

    @Column(name = "TGMuon")
    private LocalDateTime borrowTime;

    @Column(name = "TGTra")
    private LocalDateTime returnTime;

}
