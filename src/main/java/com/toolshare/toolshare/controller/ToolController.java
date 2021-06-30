package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.entity.File;
import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.repository.ToolRepository;
import com.toolshare.toolshare.entity.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Luca
 */

@Controller
@RequestMapping(path="/api/tool")
public class ToolController {
    @Autowired
    private ToolRepository toolRepository;

    //
    // POST-REQUESTS
    //

    /**
     * Create new Tool
     * @param user user id of user that owns the tool
     * @param name name of the tool
     * @param category category of the tool
     * @param description description about the tool
     * @param image image id of selected picture for the tool
     * @param isAvailable true/false if tool is available for a loan
     * @param latitude
     * @param longitude
     * @return Success message or error exception
     */
    @PostMapping(path="/add")
    public @ResponseBody
    String addNewTool (@RequestParam User user,
                       @RequestParam String name,
                       @RequestParam String category,
                       @RequestParam String description,
                       @RequestParam String image,
                       @RequestParam boolean isAvailable,
                       @RequestParam double latitude,
                       @RequestParam double longitude
                       ) {

        Tool n = new Tool();
        n.setUser(user);
        n.setName(name);
        n.setCategory(category);
        n.setDescription(description);
        n.setImage(image);
        n.setAvailable(isAvailable);
        n.setLatitude(latitude);
        n.setLongitude(longitude);

        try {
            toolRepository.save(n);
            System.out.println("Tool created: " + n.toString());
            return "Tool created";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Edit existing tool
     * @param tool Tool object with changes
     * @return Success message or error exception
     */
    @PutMapping(path="/edit")
    public @ResponseBody String updateTool(@Valid @RequestBody Tool tool) {
        try {
            toolRepository.save(tool);
            return "Updated";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Set Tool visible and available to other users
     * @param id tool id to set available
     * @param available true or false if it should be available
     * @return Success message or error exception
     */
    @PutMapping(path="/setavailable")
    public @ResponseBody String setToolAvailable(@RequestParam int id, @RequestParam boolean available) {
        try {
            Tool tool = toolRepository.getOne(id);
            tool.setAvailable(available);
            toolRepository.save(tool);
            return "Updated";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Delete Tool
     * @param id tool id to be deleted
     * @return Success message or error exception
     */
    @DeleteMapping(value = "/del")
    public @ResponseBody String deleteTool(@RequestParam Integer id) {

        try {
            toolRepository.deleteById(id);
            return "Entry deleted!";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    /**
     * Get all Tools that are available
     * @return Array of Tool objects
     */
    @GetMapping(path="/available")
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody Iterable<Tool> getAvailableTools() {
        // This returns a JSON or XML with the users
        return toolRepository.findByIsAvailable(true);
    }

    /**
     * Get all Tools
     * @return Array of Tool objects
     */
    @GetMapping(path="/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody Iterable<Tool> getAllTools() {
        // This returns a JSON or XML with the users
        return toolRepository.findAll();
    }

    /**
     * Get all Tools of specific user
     * @param user user id of user to get all tools
     * @return Array of Tool objects
     */
    @GetMapping(path="/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody Iterable<Tool> getUserTools(@RequestParam User user) {
        // This returns a JSON or XML with the users
        return toolRepository.findByUser(user);
    }


}
