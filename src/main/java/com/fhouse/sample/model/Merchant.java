package com.fhouse.sample.model;

import com.fhouse.sample.model.audit.AuditBase;
import com.fhouse.sample.model.enums.OperationType;
import com.fhouse.sample.model.enums.Status;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MERCHANT")
@SQLDelete(sql = "update MERCHANT SET DATA_STATUS = 'DELETED' WHERE id = ? AND version = ?")
@Where(clause = "DATA_STATUS <> 'DELETED'")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class Merchant extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MERCHANT_ID")
    @SequenceGenerator(name = "SEQ_MERCHANT_ID", sequenceName = "SEQ_MERCHANT_ID", allocationSize = 1, initialValue = 3)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATUS")
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "OPERATION_TYPE")
    private OperationType operation;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "CHANNEL")
    private String channel;

    @Column(name = "CUSTOM_DATA")
    private String customData;

    @Column(name = "CHAIN_ID")
    private String chainId;

    @Column(name = "AGENT_INFO_ID")
    private String agentInfoId;

    @Column(name = "FX_TRANSACTION_ID")
    private String fxTransactionId;

    @Column(name = "ACQUIRER_TRANSACTION_ID")
    private String acquirerTransactionId;

    @Column(name = "CODE")
    private String code;


}
