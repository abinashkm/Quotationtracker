package com.abinash.quotationtracker.controller;

import com.abinash.quotationtracker.dto.request.SubmitQuoteRequest;
import com.abinash.quotationtracker.dto.response.QuoteResponse;
import com.abinash.quotationtracker.service.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping
    public QuoteResponse submitQuote(@Valid @RequestBody SubmitQuoteRequest request) {
        return quoteService.submitQuote(request);
    }
}
