package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.entity.Loan;
import com.toolshare.toolshare.entity.Rating;
import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/rating")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Rating> getAllRatings() {
        // This returns a JSON or XML with the users
        return ratingRepository.findAll();
    }

    @GetMapping(path="/user")
    public @ResponseBody Iterable<Rating> getAllUserRatings(@RequestParam User user) {
        // This returns a JSON or XML with the users
        return ratingRepository.findAllByUser(user);
    }

    @GetMapping(path="/tool")
    public @ResponseBody Iterable<Rating> getAllToolRatings(@RequestParam Tool tool) {
        // This returns a JSON or XML with the users
        return ratingRepository.findAllByTool(tool);
    }
}
