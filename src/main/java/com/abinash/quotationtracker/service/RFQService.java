package com.abinash.quotationtracker.service;

import com.abinash.quotationtracker.dto.request.CreateRFQRequest;
import com.abinash.quotationtracker.dto.response.RFQResponse;

import java.util.List;

public interface RFQService {

    RFQResponse createRFQ(CreateRFQRequest request);

    List<RFQResponse> getRFQsForCurrentUser();
}
