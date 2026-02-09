package com.abinash.quotationtracker.service.impl;

import com.abinash.quotationtracker.dto.request.CreateRFQRequest;
import com.abinash.quotationtracker.dto.response.RFQResponse;
import com.abinash.quotationtracker.entity.RFQ;
import com.abinash.quotationtracker.entity.User;
import com.abinash.quotationtracker.enums.RFQStatus;
import com.abinash.quotationtracker.exception.ResourceNotFoundException;
import com.abinash.quotationtracker.repository.RFQRepository;
import com.abinash.quotationtracker.repository.UserRepository;
import com.abinash.quotationtracker.security.SecurityUtil;
import com.abinash.quotationtracker.service.RFQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RFQServiceImpl implements RFQService {

    private final RFQRepository rfqRepository;
    private final UserRepository userRepository;

    // ---------------- CREATE RFQ ----------------
    @Override
    public RFQResponse createRFQ(CreateRFQRequest request) {

        Long userId = SecurityUtil.getCurrentUserId();

        User customer = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        RFQ rfq = new RFQ();
        rfq.setTitle(request.getTitle());
        rfq.setDescription(request.getDescription());
        rfq.setCustomer(customer);

        RFQ saved = rfqRepository.save(rfq);

        return mapToResponse(saved);
    }

    // ---------------- GET RFQs ----------------
    @Override
    public List<RFQResponse> getRFQsForCurrentUser() {

        Long userId = SecurityUtil.getCurrentUserId();
        String role = SecurityUtil.getCurrentUserRole();

        if ("ROLE_CUSTOMER".equals(role)) {
            User customer = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            return rfqRepository.findByCustomer(customer)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
        }

        if ("ROLE_VENDOR".equals(role)) {
            return rfqRepository.findByStatus(RFQStatus.OPEN)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
        }

        throw new RuntimeException("Invalid role");
    }

    // ---------------- HELPER MAPPER ----------------
    private RFQResponse mapToResponse(RFQ rfq) {
        RFQResponse response = new RFQResponse();
        response.setId(rfq.getId());
        response.setTitle(rfq.getTitle());
        response.setDescription(rfq.getDescription());
        response.setStatus(rfq.getStatus());
        response.setCustomerId(rfq.getCustomer().getId());
        return response;
    }
}
