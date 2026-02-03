package com.abinash.quotationtracker.repository;

import com.abinash.quotationtracker.entity.Quote;
import com.abinash.quotationtracker.entity.RFQ;
import com.abinash.quotationtracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findByRfq(RFQ rfq);

    Optional<Quote> findByRfqAndVendor(RFQ rfq, User vendor);
}