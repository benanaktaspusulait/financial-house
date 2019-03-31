package com.fhouse.sample.payload.response;

import com.fhouse.sample.model.enums.TransactionStatus;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Currency;
import java.util.Date;
import java.util.List;

@Data
public class TransactionReportResponse {

    private TransactionStatus status;
    private List<TransactionReport> response;

}
