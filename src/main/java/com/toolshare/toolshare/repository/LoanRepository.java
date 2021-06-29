package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toolshare.toolshare.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Iterable<Loan> findAllByUser(User user);
    Iterable<Loan> findByTool(Tool tool);
    Iterable<Loan> findAllByUserAndLoanStatus(User user, String loan_status);

    @Query(value = "SELECT l FROM Loan l WHERE l.tool.user.id = ?1")
    Iterable<Loan> findAllByToolUser(Long user_id);
}
