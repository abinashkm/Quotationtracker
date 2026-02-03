package com.abinash.quotationtracker.controller;

import com.abinash.quotationtracker.dto.request.CreateRFQRequest;
import com.abinash.quotationtracker.dto.response.RFQResponse;
import com.abinash.quotationtracker.service.RFQService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rfqs")
@RequiredArgsConstructor
public class RFQController {

    private final RFQService rfqService;

    @PostMapping
    public RFQResponse createRFQ(@Valid @RequestBody CreateRFQRequest request) {
        return rfqService.createRFQ(request);
    }
}
