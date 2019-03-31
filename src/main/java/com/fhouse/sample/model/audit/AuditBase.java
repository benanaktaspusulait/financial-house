package com.fhouse.sample.model.audit;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fhouse.sample.model.enums.DataStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "lastModifiedBy"},
        allowGetters = true
)
public abstract class AuditBase extends ModelBase {

    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    @CreatedDate
    @Column(name = "CREATED_AT")
    public Instant createdAt;

    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private Instant updatedAt;

    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    @LastModifiedDate
    @Column(name = "DELETED_AT")
    private Instant deletedAt;

    @Basic(fetch = FetchType.LAZY)
    @NotNull
    @JsonIgnore
    @CreatedBy
    @Column(name = "CREATE_USER")
    private String createdBy;

    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    @LastModifiedBy
    @Column(name = "UPDATE_USER")
    private String lastModifiedBy;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "DATA_STATUS")
    @Enumerated(EnumType.STRING)
    private DataStatus dataStatus = DataStatus.ACTIVE;

    @Basic(fetch = FetchType.LAZY)
    @NotNull
    @JsonIgnore
    @Version
    @Column(name = "VERSION")
    private Long version;
}