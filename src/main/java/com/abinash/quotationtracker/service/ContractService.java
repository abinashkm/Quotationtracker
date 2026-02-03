package com.abinash.quotationtracker.service;

import com.abinash.quotationtracker.dto.request.CreateContractRequest;
import com.abinash.quotationtracker.dto.response.ContractResponse;

public interface ContractService {

    ContractResponse createContract(CreateContractRequest request);
}
