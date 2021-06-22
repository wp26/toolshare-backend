package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.entity.Loan;
import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/loan")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    //
    // POST-REQUESTS
    //

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewLoan (@RequestParam String message,
                       @RequestParam Date request_date,
                       @RequestParam int loan_days,
                       @RequestParam Tool tool_id,
                       @RequestParam User user) {

        Loan n = new Loan();
        n.setMessage(message);
        n.setRequestDate(request_date);
        n.setLoanDays(loan_days);
        n.setTool(tool_id);
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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Loan> getAllLoans() {
        // This returns a JSON or XML with the users
        return loanRepository.findAll();
    }

    @GetMapping(path="/user")
    public @ResponseBody Iterable<Loan> getAllUserLoans(@RequestParam User user) {
        // This returns a JSON or XML with the users
        return loanRepository.findAllByUser(user);
    }

    @GetMapping(path="/tool")
    public @ResponseBody Iterable<Loan> getAllToolLoans(@RequestParam Tool tool) {
        // This returns a JSON or XML with the users
        return loanRepository.findByTool(tool);
    }

    @GetMapping(path="/user/accepted")
    public @ResponseBody Iterable<Loan> getAllToolLoans(@RequestParam User user, @RequestParam boolean accepted) {
        // This returns a JSON or XML with the users
        return loanRepository.findAllByUserAndRequestAccepted(user, accepted);
    }

}
