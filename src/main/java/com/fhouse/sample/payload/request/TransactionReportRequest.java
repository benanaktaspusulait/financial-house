package com.fhouse.sample.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class TransactionReportRequest {

    @NotBlank
    private Date fromDate;

    @NotBlank
    private Date toDate;

    private Long merchantId;

    private Long acquirerId;


}
