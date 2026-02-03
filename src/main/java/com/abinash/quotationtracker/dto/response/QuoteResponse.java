package com.abinash.quotationtracker.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteResponse {

    private Long id;
    private Double amount;
    private Integer durationInDays;
    private Long rfqId;
    private Long vendorId;
}
