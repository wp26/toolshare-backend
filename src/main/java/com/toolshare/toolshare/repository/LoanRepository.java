package com.toolshare.toolshare.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toolshare.toolshare.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
