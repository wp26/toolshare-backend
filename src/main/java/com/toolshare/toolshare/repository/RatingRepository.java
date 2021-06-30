package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.entity.Loan;
import com.toolshare.toolshare.entity.Rating;
import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for all Files and custom Query's
 *
 * @author
 */

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    /**
     * Get all Ratings of a user
     * @param user user id of user
     * @return Array of Rating objects
     */
    Iterable<Rating> findAllByUser(User user);


    /**
     * Get all Rating of a tool
     * @param tool tool id of tool
     * @return Array of Rating objects
     */

    Iterable<Rating> findAllByTool(Tool tool);
    /**
     * Get all Rating of a loan
     * @param loan loan id of loan
     * @return Array of Rating objects
     */
    Iterable<Rating> findAllByLoan(Loan loan);
}
