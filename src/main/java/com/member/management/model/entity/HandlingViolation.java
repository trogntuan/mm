package com.member.management.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "xuly")
public class HandlingViolation {

    @Id
    @Column(name = "MaXL", nullable = false, length = 10)
    private String id;

    @ManyToOne
    @JoinColumn(name = "MaTV", referencedColumnName = "MaTV", nullable = false)
    private Member member;

    @Column(name = "HinhThucXL", length = 250)
    @Nationalized
    private String handlingMethod;

    @Column(name = "SoTien")
    private int amount;

    @Column(name = "NgayXL")
    private LocalDateTime handlingDate;

    @Column(name = "TrangThaiXL")
    private int status;

}
