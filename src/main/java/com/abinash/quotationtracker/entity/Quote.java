package com.abinash.quotationtracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "quotes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"rfq_id", "vendor_id"})
)
@Getter
@Setter
public class Quote extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Integer durationInDays;

    @ManyToOne
    @JoinColumn(name = "rfq_id")
    private RFQ rfq;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private User vendor;
}
