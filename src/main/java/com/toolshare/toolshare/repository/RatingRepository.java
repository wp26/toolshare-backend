package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.entity.Loan;
import com.toolshare.toolshare.entity.Rating;
import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Iterable<Rating> findAllByUser(User user);
    Iterable<Rating> findAllByTool(Tool tool);
    Iterable<Rating> findAllByLoan(Loan loan);
}
