package com.fhouse.sample.model;


import com.fhouse.sample.model.audit.AuditBase;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "AGENT")
@SQLDelete(sql = "update AGENT SET DATA_STATUS = 'DELETED' WHERE id = ? AND version = ?")
@Where(clause = "DATA_STATUS <> 'DELETED'")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class Agent extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AGENT_ID")
    @SequenceGenerator(name = "SEQ_AGENT_ID", sequenceName = "SEQ_AGENT_ID", allocationSize = 1, initialValue = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CUSTOMER_IP")
    private String customerIp;

    @Column(name = "CUSTOMER_USER_AGENT")
    private String customerUserAgent;

    @Column(name = "MERCHANT_IP")
    private String merchantIp;

}
