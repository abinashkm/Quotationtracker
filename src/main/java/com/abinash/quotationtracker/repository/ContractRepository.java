package com.abinash.quotationtracker.repository;


import com.abinash.quotationtracker.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}