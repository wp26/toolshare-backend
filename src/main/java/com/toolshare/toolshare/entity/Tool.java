package com.toolshare.toolshare.entity;

import org.springframework.hateoas.Link;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Luca
 */

@Entity
@Table(name = "tools")
public class Tool implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Link user_id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String category;
    
    @Size(max = 350)
    private String description;

    private Long image;

    @Column(name="is_available")
    private boolean isAvailable;

    public Tool () { }

    public Tool (String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;

    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Link getUser_id() {
        return user_id;
    }

    public void setUser_id(Link user_id) { this.user_id = user_id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getImage() { return image; }

    public void setImage(Long image) { this.image = image; }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

