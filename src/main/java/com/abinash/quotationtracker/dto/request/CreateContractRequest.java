package com.abinash.quotationtracker.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CreateContractRequest {

    @NotNull(message = "RFQ ID is required")
    private Long rfqId;

    @NotNull(message = "Quote ID is required")
    private Long quoteId;
}
