package com.fhouse.sample.payload.response;


import lombok.Data;

import java.util.Currency;

@Data
public class TransactionReport{

    private Integer count;
    private Integer total;
    private Currency currency;


}