package com.abinash.quotationtracker.controller;

import com.abinash.quotationtracker.dto.request.CreateRFQRequest;
import com.abinash.quotationtracker.dto.response.RFQResponse;
import com.abinash.quotationtracker.service.RFQService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rfqs")
@RequiredArgsConstructor
public class RFQController {

    private final RFQService rfqService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public RFQResponse createRFQ(@Valid @RequestBody CreateRFQRequest request) {
        return rfqService.createRFQ(request);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'VENDOR')")
    public List<RFQResponse> getRFQs() {
        return rfqService.getRFQsForCurrentUser();
    }

}
