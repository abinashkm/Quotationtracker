package com.abinash.quotationtracker.service;

import com.abinash.quotationtracker.dto.request.SubmitQuoteRequest;
import com.abinash.quotationtracker.dto.response.QuoteResponse;

public interface QuoteService {

    QuoteResponse submitQuote(SubmitQuoteRequest request);
}
