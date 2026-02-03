package com.abinash.quotationtracker.service.impl;

import com.abinash.quotationtracker.dto.request.CreateContractRequest;
import com.abinash.quotationtracker.dto.response.ContractResponse;
import com.abinash.quotationtracker.entity.Contract;
import com.abinash.quotationtracker.entity.Quote;
import com.abinash.quotationtracker.entity.RFQ;
import com.abinash.quotationtracker.enums.RFQStatus;
import com.abinash.quotationtracker.exception.BadRequestException;
import com.abinash.quotationtracker.exception.ResourceNotFoundException;
import com.abinash.quotationtracker.repository.ContractRepository;
import com.abinash.quotationtracker.repository.QuoteRepository;
import com.abinash.quotationtracker.repository.RFQRepository;
import com.abinash.quotationtracker.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final RFQRepository rfqRepository;
    private final QuoteRepository quoteRepository;

    @Override
    public ContractResponse createContract(CreateContractRequest request) {

        RFQ rfq = rfqRepository.findById(request.getRfqId())
                .orElseThrow(() -> new ResourceNotFoundException("RFQ not found"));

        if (rfq.getStatus() != RFQStatus.OPEN) {
            throw new BadRequestException("Contract already created for this RFQ");
        }

        Quote quote = quoteRepository.findById(request.getQuoteId())
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found"));

        if (!quote.getRfq().getId().equals(rfq.getId())) {
            throw new BadRequestException("Quote does not belong to this RFQ");
        }

        Contract contract = new Contract();
        contract.setRfq(rfq);
        contract.setSelectedQuote(quote);

        Contract saved = contractRepository.save(contract);

        rfq.setStatus(RFQStatus.CONTRACT_CREATED);
        rfqRepository.save(rfq);

        ContractResponse response = new ContractResponse();
        response.setId(saved.getId());
        response.setRfqId(rfq.getId());
        response.setQuoteId(quote.getId());

        return response;
    }
}
