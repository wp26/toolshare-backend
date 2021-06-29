package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.entity.Loan;
import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

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
                       @RequestParam Date requestDate,
                       @RequestParam int loanDays,
                       @RequestParam Tool tool,
                       @RequestParam User user) {

        Loan n = new Loan();
        n.setMessage(message);
        n.setRequestDate(requestDate);
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

    @PutMapping(path="/setstatus")
    public @ResponseBody String setLoanStatus(@RequestParam Long id, @RequestParam String status) {
        try {
            Loan loan = loanRepository.getOne(id);
            loan.setLoanStatus(status);
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
    @GetMapping(path="/tool/user")
    public @ResponseBody Iterable<Loan> getAllToolUser(@RequestParam Long user_id) {
        // This returns a JSON or XML with the users
        return loanRepository.findAllByToolUser(user_id);
    }

    @GetMapping(path="/user/status")
    public @ResponseBody Iterable<Loan> getAllToolLoans(@RequestParam User user, @RequestParam String status) {
        // This returns a JSON or XML with the users
        return loanRepository.findAllByUserAndLoanStatus(user, status);
    }

    @GetMapping(path="/user/open")
    public @ResponseBody Iterable<Loan> getAllToolLoans(@RequestParam User user) {
        // This returns a JSON or XML with the users
        return loanRepository.findAllByUserAndLoanStatus(user, "OPEN");
    }
}
