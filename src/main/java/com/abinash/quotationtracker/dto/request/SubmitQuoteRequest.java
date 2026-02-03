package com.abinash.quotationtracker.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class SubmitQuoteRequest {

    @NotNull
    private Long rfqId;

    @Min(1)
    private Double amount;

    @Min(1)
    private Integer durationInDays;
}
