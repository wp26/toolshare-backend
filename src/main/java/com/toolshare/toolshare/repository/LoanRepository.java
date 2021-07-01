package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toolshare.toolshare.entity.Loan;

/**
 * Repository for all Files and custom Query's
 *
 * @author Paul
 */

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    /**
     * Get all Loans for a user
     * @param user user id of user
     * @return Array of Loan objects
     */
    Iterable<Loan> findAllByUser(User user);

    /**
     * Get all Loans for a tool
     * @param tool tool id of tool
     * @return Array of Loan objects
     */
    Iterable<Loan> findByTool(Tool tool);

    /**
     * Get all Loans for a user and a status
     * @param user user id of user
     * @param loan_status status that is looked for
     * @return Array of Loan objects
     */
    Iterable<Loan> findAllByUserAndLoanStatus(User user, String loan_status);

    /**
     * Get all Loans for all tools of a user
     * @param user_id user id of user
     * @return Array of Loan objects
     */
    @Query(value = "SELECT l FROM Loan l WHERE l.tool.user.id = ?1")
    Iterable<Loan> findAllByToolUser(Long user_id);
}
