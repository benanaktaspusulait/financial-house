package com.fhouse.sample.model;

import com.fhouse.sample.model.enums.Channel;
import com.fhouse.sample.model.enums.OperationType;
import com.fhouse.sample.model.enums.Status;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@Embeddable
public class TransactionResult {

    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "STATUS")
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "CHANNEL")
    private Channel channel;

    @Column(name = "CUSTOM_DATA")
    private String customData;

    @Column(name = "CHAIN_ID")
    private String chainId;

    @Column(name = "AGENT_INFO_ID")
    private String agentInfoId;

    @Column(name = "OPERATION")
    private OperationType operation;

    @Column(name = "FX_TRANSACTION_ID")
    private String fxTransactionId;

    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Column(name = "CREATED_AT")
    private Date createdAt;

}
