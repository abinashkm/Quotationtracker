package com.abinash.quotationtracker.service.impl;

import com.abinash.quotationtracker.dto.request.SubmitQuoteRequest;
import com.abinash.quotationtracker.dto.response.QuoteResponse;
import com.abinash.quotationtracker.entity.Quote;
import com.abinash.quotationtracker.entity.RFQ;
import com.abinash.quotationtracker.entity.User;
import com.abinash.quotationtracker.enums.RFQStatus;
import com.abinash.quotationtracker.exception.BadRequestException;
import com.abinash.quotationtracker.exception.ResourceNotFoundException;
import com.abinash.quotationtracker.repository.QuoteRepository;
import com.abinash.quotationtracker.repository.RFQRepository;
import com.abinash.quotationtracker.repository.UserRepository;
import com.abinash.quotationtracker.security.SecurityUtil;
import com.abinash.quotationtracker.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final RFQRepository rfqRepository;
    private final UserRepository userRepository;

    @Override
    public QuoteResponse submitQuote(SubmitQuoteRequest request) {

        RFQ rfq = rfqRepository.findById(request.getRfqId())
                .orElseThrow(() -> new ResourceNotFoundException("RFQ not found"));

        if (rfq.getStatus() != RFQStatus.OPEN) {
            throw new BadRequestException("Cannot submit quote. RFQ is closed");
        }

        Long vendorId = SecurityUtil.getCurrentUserId();

        User vendor = userRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        quoteRepository.findByRfqAndVendor(rfq, vendor)
                .ifPresent(q -> {
                    throw new BadRequestException("Vendor has already submitted a quote");
                });

        Quote quote = new Quote();
        quote.setRfq(rfq);
        quote.setVendor(vendor);
        quote.setAmount(request.getAmount());
        quote.setDurationInDays(request.getDurationInDays());

        Quote saved = quoteRepository.save(quote);

        QuoteResponse response = new QuoteResponse();
        response.setId(saved.getId());
        response.setAmount(saved.getAmount());
        response.setDurationInDays(saved.getDurationInDays());
        response.setRfqId(rfq.getId());
        response.setVendorId(vendor.getId());

        return response;
    }
}
