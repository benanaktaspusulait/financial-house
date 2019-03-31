package com.fhouse.sample.model.dto;

import com.fhouse.sample.model.audit.AuditBase;
import com.fhouse.sample.model.enums.AcquirerType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Data
public class AcquirerDTO extends AuditBase {

    private Long id;
    private String code;
    private String name;
    private AcquirerType type;

    public AcquirerDTO() {
    }

    public AcquirerDTO(String code, String name, AcquirerType type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }
}
