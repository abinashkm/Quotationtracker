package com.abinash.quotationtracker.controller;

import com.abinash.quotationtracker.dto.request.CreateContractRequest;
import com.abinash.quotationtracker.dto.response.ContractResponse;
import com.abinash.quotationtracker.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    public ContractResponse createContract(@Valid @RequestBody CreateContractRequest request) {
        return contractService.createContract(request);
    }
}
