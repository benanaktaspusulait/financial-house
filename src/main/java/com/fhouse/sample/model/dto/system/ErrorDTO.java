package com.fhouse.sample.model.dto.system;

import lombok.Data;

import java.io.Serializable;


@Data
public class ErrorDTO implements Serializable {

    private String error;
    private String error_description;

    public ErrorDTO(String error, String error_description) {
        this.setError(error);
        this.setError_description(error_description);
    }

    public interface ErrorCodes{

        public static final String SUCCESS = "0";
        public static final String GENERAL = "100";


    }

}