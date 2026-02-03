package com.abinash.quotationtracker.dto.response;

import com.abinash.quotationtracker.enums.RFQStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RFQResponse {

    private Long id;
    private String title;
    private String description;
    private RFQStatus status;
    private Long customerId;
}
