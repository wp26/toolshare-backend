package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.entity.Loan;
import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
                       @RequestParam Date request_date,
                       @RequestParam int loan_days,
                       @RequestParam Tool tool_id,
                       @RequestParam User user_id) {

        Loan n = new Loan();
        n.setMessage(message);
        n.setRequest_date(request_date);
        n.setLoan_days(loan_days);
        n.setTool_id(tool_id);
        n.setUser_id(user_id);

        try {
            loanRepository.save(n);
            System.out.println("Loan created: " + n.toString());
            return "Loan created";
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




}
