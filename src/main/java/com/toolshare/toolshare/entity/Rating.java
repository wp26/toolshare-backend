package com.toolshare.toolshare.entity;

/**
 * @author Christopher
 */

import org.springframework.hateoas.Link;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ratings",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "title")
        })

public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Link user_id;
    private Link loan_id;
    private Link tool_id;

    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    @Size(max = 300)
    private String text;

    @Size(max = +1)
    private Integer ratingUp;

    @Size(max = -1)
    private Integer ratingDown;

    public Rating(String title, String text, Integer ratingUp, Integer ratingDown) {
        this.title = title;
        this.text = text;
        this.ratingUp = ratingUp;
        this.ratingDown = ratingDown;
    }

    public Integer getId() {
        return id;
    }

    public Link getUser_id() {
        return user_id;
    }

    public void setUser_id(Link user_id) {
        this.user_id = user_id;
    }

    public Link getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Link loan_id) {
        this.loan_id = loan_id;
    }

    public Link getTool_id() {
        return tool_id;
    }

    public void setTool_id(Link tool_id) {
        this.tool_id = tool_id;
    }

    public void setId(Integer id) {
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
}
