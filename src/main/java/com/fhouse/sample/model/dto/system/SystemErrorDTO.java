package com.fhouse.sample.model.dto.system;

import com.fhouse.sample.model.enums.ErrorType;
import com.fhouse.sample.model.error.SystemError;
import lombok.Data;

import java.io.Serializable;


@Data
public class SystemErrorDTO implements Serializable {

    private Long id;
    private ErrorType errorType;
    private String username;
    private String errorDescription;

    public SystemErrorDTO(){
    }

    public SystemErrorDTO(ErrorType errorType, String username){
        this.errorType = errorType;
        this.username = username;
    }

    public SystemErrorDTO(ErrorType errorType, String username, String errorDescription){
        this.errorType = errorType;
        this.username = username;
        this.errorDescription = errorDescription;
    }



    public static SystemError toEntity(SystemErrorDTO dto) {

        SystemError entity = new SystemError();
        entity.setId(dto.getId());
        entity.setErrorType(dto.getErrorType());
        entity.setUsername(dto.getUsername());
        entity.setErrorDescription(dto.getErrorDescription());
        return entity;

    }


}
