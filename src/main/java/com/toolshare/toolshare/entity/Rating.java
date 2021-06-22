package com.toolshare.toolshare.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author Christopher
 */


@Entity
@Table(name = "ratings")

public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    @Size(max = 300)
    private String text;

    private Integer ratingUp;

    private Integer ratingDown;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_id", referencedColumnName = "id")
    private Tool tool;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", ratingUp=" + ratingUp + '\'' +
                ", ratingDown=" + ratingDown + '\'' +
                ", user=" + user + '\'' +
                ", tool=" + tool + '\'' +
                ", loan=" + loan + '\'' +
                '}';
    }

    public Rating() {

    }

    public Rating(String title, String text, Integer ratingUp, Integer ratingDown, User user, Tool tool, Loan loan) {
        this.title = title;
        this.text = text;
        this.ratingUp = ratingUp;
        this.ratingDown = ratingDown;
        this.user = user;
        this.tool = tool;
        this.loan = loan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRatingUp() {
        return ratingUp;
    }

    public void setRatingUp(Integer ratingUp) {
        this.ratingUp = ratingUp;
    }

    public Integer getRatingDown() {
        return ratingDown;
    }

    public void setRatingDown(Integer ratingDown) {
        this.ratingDown = ratingDown;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
