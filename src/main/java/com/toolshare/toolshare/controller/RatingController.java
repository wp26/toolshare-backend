package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.entity.Loan;
import com.toolshare.toolshare.entity.Rating;
import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * requests for Entity Rating
 *
 * @author Christopher
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/rating")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Create new Rating
     * @param title short header for the rating
     * @param text textbody of rating
     * @param ratingUp
     * @param ratingDown
     * @param user user id of rating user
     * @param tool tool id of tool that get´s rated
     * @param loan loan id of loan that get´s rated
     * @return Success message or error exception
     */
    @PostMapping(path="/add")
    public @ResponseBody
    String addNewRating (@RequestParam String title,
                         @RequestParam String text,
                         @RequestParam Integer ratingUp,
                         @RequestParam Integer ratingDown,
                         @RequestParam User user,
                         @RequestParam Tool tool,
                         @RequestParam Loan loan) {

        Rating n = new Rating();
        n.setTitle(title);
        n.setText(text);
        n.setRatingUp(ratingUp);
        n.setRatingUp(ratingDown);
        n.setLoan(loan);
        n.setTool(tool);
        n.setUser(user);

        try {
            ratingRepository.save(n);
            System.out.println("Rating created: " + n.toString());
            return "Rating created";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Edit existing Rating
     * @param rating Rating objects with changes
     * @return Success message or error exception
     */
    @PutMapping(path="/edit")
    public @ResponseBody String updateLoan(@Valid @RequestBody Rating rating) {
        try {
            ratingRepository.save(rating);
            return "Updated";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Delete a rating
     * @param id rating id to be deleted
     * @return Success message or error exception
     */
    @DeleteMapping(value = "/del")
    public @ResponseBody String deleteLoan(@RequestParam Long id) {
        try {
            ratingRepository.deleteById(id);
            return "Entry deleted!";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Get all Ratings
     * @return Array of Rating objects
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Rating> getAllRatings() {
        // This returns a JSON or XML with the users
        return ratingRepository.findAll();
    }

    /**
     * Get Ratings for specific user
     * @param user user id for requested user
     * @return Array of Rating objects
     */
    @GetMapping(path="/user")
    public @ResponseBody Iterable<Rating> getAllUserRatings(@RequestParam User user) {
        // This returns a JSON or XML with the users
        return ratingRepository.findAllByUser(user);
    }

    /**
     * Get Ratings for specific tool
     * @param tool tool id for requested tool
     * @return Array of Rating objects
     */
    @GetMapping(path="/tool")
    public @ResponseBody Iterable<Rating> getAllToolRatings(@RequestParam Tool tool) {
        // This returns a JSON or XML with the users
        return ratingRepository.findAllByTool(tool);
    }
}
