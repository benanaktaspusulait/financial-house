package com.fhouse.sample.model;

import com.fhouse.sample.model.audit.AuditBase;
import com.fhouse.sample.model.enums.Currency;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TRANSACTIONS")
@SQLDelete(sql = "update TRANSACTIONS SET DATA_STATUS = 'DELETED' WHERE id = ? AND version = ?")
@Where(clause = "DATA_STATUS <> 'DELETED'")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class Transaction extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACTIONS_ID")
    @SequenceGenerator(name = "SEQ_TRANSACTIONS_ID", sequenceName = "SEQ_TRANSACTIONS_ID", allocationSize = 1, initialValue = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ORIGINAL_AMOUNT")
    private Double originalAmount;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ORIGINAL_CURRENCY")
    private Currency originalCurrency;


    private String name;

}
