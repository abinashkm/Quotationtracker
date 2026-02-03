package com.abinash.quotationtracker.service.impl;

import com.abinash.quotationtracker.dto.request.CreateRFQRequest;
import com.abinash.quotationtracker.dto.response.RFQResponse;
import com.abinash.quotationtracker.entity.RFQ;
import com.abinash.quotationtracker.entity.User;
import com.abinash.quotationtracker.exception.ResourceNotFoundException;
import com.abinash.quotationtracker.repository.RFQRepository;
import com.abinash.quotationtracker.repository.UserRepository;
import com.abinash.quotationtracker.service.RFQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RFQServiceImpl implements RFQService {

    // THESE TWO FIELDS WERE MISSING
    private final RFQRepository rfqRepository;
    private final UserRepository userRepository;

    @Override
    public RFQResponse createRFQ(CreateRFQRequest request) {

        User customer = userRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        RFQ rfq = new RFQ();
        rfq.setTitle(request.getTitle());
        rfq.setDescription(request.getDescription());
        rfq.setCustomer(customer);

        RFQ saved = rfqRepository.save(rfq);

        RFQResponse response = new RFQResponse();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setStatus(saved.getStatus());
        response.setCustomerId(customer.getId());

        return response;
    }
}
