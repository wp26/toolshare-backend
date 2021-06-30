package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.entity.Loan;
import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.text.SimpleDateFormat;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/loan")
public class LoanController {


    @Autowired
    private LoanRepository loanRepository;

    //
    // POST-REQUESTS
    //

    /**
     * Create new loan
     * @param message message to user from requesting user
     * @param loanDays number of days for the loan
     * @param tool ID of desired tool
     * @param user ID of requesting user
     * @return Success message or error exception
     */
    @PostMapping(path="/add")
    public @ResponseBody
    String addNewLoan (@RequestParam String message,
                       @RequestParam int loanDays,
                       @RequestParam Tool tool,
                       @RequestParam User user) {

        Loan n = new Loan();
        n.setMessage(message);
        n.setRequestDate(new Date());
        n.setLoanStatus("open");
        n.setLoanDays(loanDays);
        n.setTool(tool);
        n.setUser(user);

        try {
            loanRepository.save(n);
            System.out.println("Loan created: " + n.toString());
            return "Loan created";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Edit existing loan
     * @param loan Loan object with changes
     * @return Success message or error exception
     */
    @PutMapping(path="/edit")
    public @ResponseBody String updateLoan(@Valid @RequestBody Loan loan) {
        try {
            loanRepository.save(loan);
            return "Updated";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Change the state of a loan
     * @param id loan id to change
     * @param status new status of loan
     * @return Success message or error exception
     */
    @PutMapping(path="/setstatus")
    public @ResponseBody String setLoanStatus(@RequestParam Long id, @RequestParam String status) {
        try {
            Loan loan = loanRepository.getOne(id);
            loan.setLoanStatus(status);
            if (status.equalsIgnoreCase("accepted")) {
                loan.setAcceptedDate(new Date());
            }
            else if (status.equalsIgnoreCase("denied")) {
                loan.setAcceptedDate(new Date());
            }
            else if (status.equalsIgnoreCase("returned")) {
                loan.setReturnedDate(new Date());
            }
            loanRepository.save(loan);
            return "Updated";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Delete Loan
     * @param id loan id to delete
     * @return Success message or error exception
     */
    @DeleteMapping(value = "/del")
    public @ResponseBody String deleteLoan(@RequestParam Long id) {
        try {
            loanRepository.deleteById(id);
            return "Entry deleted!";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }


    //
    // GET-REQUESTS
    //

    /**
     * Get all Loans
     * @return Array of Loan objects
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Loan> getAllLoans() {
        // This returns a JSON or XML with the users
        return loanRepository.findAll();
    }

    /**
     * Get all Loans of a specific user
     * @param user user id to show all loans
     * @return Array of Loan objects
     */
    @GetMapping(path="/user")
    public @ResponseBody Iterable<Loan> getAllUserLoans(@RequestParam User user) {
        // This returns a JSON or XML with the users
        return loanRepository.findAllByUser(user);
    }

    /**
     * Get all Loans of a specific tool
     * @param tool tool id to show all loans
     * @return Array of Loan objects
     */
    @GetMapping(path="/tool")
    public @ResponseBody Iterable<Loan> getAllToolLoans(@RequestParam Tool tool) {
        // This returns a JSON or XML with the users
        return loanRepository.findByTool(tool);
    }

    /**
     * Get all Loans for a users tools
     * @param user_id user id of tool
     * @return Array of Loan objects
     */
    @GetMapping(path="/tool/user")
    public @ResponseBody Iterable<Loan> getAllToolUser(@RequestParam Long user_id) {
        // This returns a JSON or XML with the users
        return loanRepository.findAllByToolUser(user_id);
    }

    /**
     * Get all Loans for a user and check status
     * @param user user id of requested user
     * @param status status to search for
     * @return Array of Loan objects
     */
    @GetMapping(path="/user/status")
    public @ResponseBody Iterable<Loan> getAllToolLoans(@RequestParam User user, @RequestParam String status) {
        // This returns a JSON or XML with the users
        return loanRepository.findAllByUserAndLoanStatus(user, status);
    }

    /**
     * Get all Loans for a user where status is "open"
     * @param user user id of requested user
     * @return Array of Loan objects
     */
    @GetMapping(path="/user/open")
    public @ResponseBody Iterable<Loan> getAllToolLoans(@RequestParam User user) {
        // This returns a JSON or XML with the users
        return loanRepository.findAllByUserAndLoanStatus(user, "open");
    }
}
