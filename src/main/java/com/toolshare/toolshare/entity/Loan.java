package com.toolshare.toolshare.entity;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Entity to store loans in DB
 *
 * @author
 */

@Entity
@Table(	name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String message;

    @NotNull
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date requestDate;

    private String loanStatus;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date acceptedDate;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date returnedDate;

    @NotNull
    private int loanDays;

    @NotNull
    @OneToOne
    @JoinColumn(name = "tool_id", referencedColumnName = "id")
    private Tool tool;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public String toString() {
        return "Loan{" +
                ", id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", loanStatus=" + loanStatus + '\'' +
                ", acceptedDate=" + acceptedDate + '\'' +
                ", returnedDate=" + returnedDate + '\'' +
                ", loanDays=" + loanDays + '\'' +
                ", tool=" + tool + '\'' +
                ", user=" + user + '\'' +
                '}';
    }

    public Loan() {

    }

    public Loan(String message, Date requestDate, int loanDays, Tool tool, User user) {
        this.message = message;
        this.requestDate = requestDate;
        this.loanDays = loanDays;
        this.tool = tool;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public int getLoanDays() {
        return loanDays;
    }

    public void setLoanDays(int loanDays) {
        this.loanDays = loanDays;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
