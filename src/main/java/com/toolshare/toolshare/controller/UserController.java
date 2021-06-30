package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.entity.Loan;
import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //
    // GET-REQUESTS
    //

    /**
     * Get all Users
     * @return Array of User objects
     */
    @GetMapping(path="/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    /**
     * Edit email of user
     * @param userID user id of user to change email
     * @param email new email string
     * @return Success message or error exception
     */
    @PutMapping(path="/edit/email")
    public @ResponseBody String updateUserEmail(@RequestParam Long userID, @RequestParam String email) {
        try {

            User user = userRepository.getOne(userID);
            user.setEmail(email);

            userRepository.save(user);
            return "Updated";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Delete user
     * @param id user id of user to be deleted
     * @return Success message or error exception
     */
    @DeleteMapping(value = "/del")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody String deleteUser(@RequestParam Long id) {

        try {
            userRepository.deleteById(id);
            return "Entry deleted!";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

}