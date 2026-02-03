package com.abinash.quotationtracker.entity;

import com.abinash.quotationtracker.enums.RFQStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rfqs")
@Getter
@Setter
public class RFQ extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private RFQStatus status = RFQStatus.OPEN;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User customer;
}
