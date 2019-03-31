package com.fhouse.sample.payload.request;

import com.fhouse.sample.model.enums.OperationType;
import com.fhouse.sample.model.enums.PaymentMethod;
import com.fhouse.sample.model.enums.TransactionStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class TransactionRequest {

    private Date fromDate;
    private Date toDate;
    private TransactionStatus status;
    private OperationType operation;
    private Long merchantId;
    private Long acquirerId;
    private PaymentMethod paymentMethod;
    private String errorCode;
    private String filterField;
    private String filterValue;
    private Integer page;
}
