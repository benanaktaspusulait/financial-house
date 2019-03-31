package com.fhouse.sample.model;

import com.fhouse.sample.model.audit.AuditBase;
import com.fhouse.sample.model.enums.AcquirerType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACQUIRER")
@SQLDelete(sql = "update ACQUIRER SET DATA_STATUS = 'DELETED' WHERE id = ? AND version = ?")
@Where(clause = "DATA_STATUS <> 'DELETED'")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class Acquirer extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACQUIRER_ID")
    @SequenceGenerator(name = "SEQ_ACQUIRER_ID", sequenceName = "SEQ_ACQUIRER_ID", allocationSize = 1, initialValue = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ACQUIRER_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private AcquirerType type;
}
