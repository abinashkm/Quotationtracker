package com.abinash.quotationtracker.service;


import com.abinash.quotationtracker.dto.request.CreateRFQRequest;
import com.abinash.quotationtracker.dto.response.RFQResponse;

public interface RFQService {
    RFQResponse createRFQ(CreateRFQRequest request);
}
