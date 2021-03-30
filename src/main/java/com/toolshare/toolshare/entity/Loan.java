package com.toolshare.toolshare.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private Date request_date;

    private boolean request_accepted;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date accepted_date;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date returned_date;

    @NotNull
    private int loan_days;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_id", referencedColumnName = "id")
    private Tool tool_id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    @Override
    public String toString() {
        return "Loan{" +
                ", id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", request_date='" + request_date + '\'' +
                ", request_accepted=" + request_accepted + '\'' +
                ", accepted_date=" + accepted_date + '\'' +
                ", returned_date=" + returned_date + '\'' +
                ", loan_days=" + loan_days + '\'' +
                ", tool_id=" + tool_id + '\'' +
                ", user_id=" + user_id + '\'' +
                '}';
    }

    public Loan() {

    }

    public Loan(String message, Date request_date, int loan_days, Tool tool_id, User user_id) {
        this.message = message;
        this.request_date = request_date;
        this.loan_days = loan_days;
        this.tool_id = tool_id;
        this.user_id = user_id;
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

    public Date getRequest_date() {
        return request_date;
    }

    public void setRequest_date(Date request_date) {
        this.request_date = request_date;
    }

    public boolean isRequest_accepted() {
        return request_accepted;
    }

    public void setRequest_accepted(boolean request_accepted) {
        this.request_accepted = request_accepted;
    }

    public Date getAccepted_date() {
        return accepted_date;
    }
    public void setAccepted_date(Date accepted_date) {
        this.accepted_date = accepted_date;
    }

    public Date getReturned_date() {
        return returned_date;
    }

    public void setReturned_date(Date returned_date) {
        this.returned_date = returned_date;
    }

    public int getLoan_days() {
        return loan_days;
    }

    public void setLoan_days(int loan_days) {
        this.loan_days = loan_days;
    }

    public Tool getTool_id() {
        return tool_id;
    }

    public void setTool_id(Tool tool_id) {
        this.tool_id = tool_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

}
