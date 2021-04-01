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
    private User user_id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_id", referencedColumnName = "id")
    private Tool tool_id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan_id;

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", ratingUp=" + ratingUp + '\'' +
                ", ratingDown=" + ratingDown + '\'' +
                ", user_id=" + user_id + '\'' +
                ", tool_id=" + tool_id + '\'' +
                ", loan_id=" + loan_id + '\'' +
                '}';
    }

    public Rating() {

    }

    public Rating(String title, String text, Integer ratingUp, Integer ratingDown, User user_id, Tool tool_id, Loan loan_id) {
        this.title = title;
        this.text = text;
        this.ratingUp = ratingUp;
        this.ratingDown = ratingDown;
        this.user_id = user_id;
        this.tool_id = tool_id;
        this.loan_id = loan_id;
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

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Tool getTool_id() {
        return tool_id;
    }

    public void setTool_id(Tool tool_id) {
        this.tool_id = tool_id;
    }

    public Loan getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Loan loan_id) {
        this.loan_id = loan_id;
    }
}
