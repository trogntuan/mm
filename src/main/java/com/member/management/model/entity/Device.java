package com.member.management.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Data
@Entity
@Table(name = "thietbi")
public class Device {

    @Id
    @Column(name = "MaTB", nullable = false, length = 10)
    private String id;

    @Column(name = "TenTB", nullable = false, length = 100)
    @Nationalized
    private String name;

    @Column(name = "MoTaTB", length = 1000)
    @Nationalized
    private String description;

    @OneToMany(mappedBy = "device")
    private List<UsageInformation> usageInformation;

}
