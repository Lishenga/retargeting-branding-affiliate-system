package com.payments_messaging.affiliate.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "commissionrate")
@Getter @Setter @ToString
public class CommissionRate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SavedUrl savedUrl;

    @Column(name = "root_id", nullable = true)
    private Long rootId;

    @Column(name = "node_id", nullable = true)
    private Long nodeId;

    @Column(name = "computed_amount", nullable = false)
    private Double computedAmount;

    public CommissionRate (){}
}
