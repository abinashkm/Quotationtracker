package com.abinash.quotationtracker.repository;

import com.abinash.quotationtracker.entity.RFQ;
import com.abinash.quotationtracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RFQRepository extends JpaRepository<RFQ, Long> {

    List<RFQ> findByCustomer(User customer);
}