package com.fhouse.sample.model.error;

import com.fhouse.sample.model.audit.AuditBase;
import com.fhouse.sample.model.enums.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "system_error")
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=false)
public class SystemError extends AuditBase implements Serializable {

    private static final long serialVersionUID = -687874117917352477L;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "error_type")
    @Enumerated(EnumType.ORDINAL)
    private ErrorType errorType;

    @Column(name = "username")
    private String username;

    @Lob
    @Type(type = "org.hibernate.type.StringType")
    @Column(name = "eror_description",columnDefinition = "LONGTEXT")
    private String errorDescription;
}