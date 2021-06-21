package com.toolshare.toolshare.controller;



import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.repository.ToolRepository;
import com.toolshare.toolshare.entity.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewTool (@RequestParam String name,
                       @RequestParam String category,
                       @RequestParam String description) {

        Tool n = new Tool();
        n.setName(name);
        n.setCategory(category);
        n.setDescription(description);

        try {
            toolRepository.save(n);
            System.out.println("Tool created: " + n.toString());
            return "Tool created";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    @GetMapping(path="/available")
    @PreAuthorize("hasRole('ROLE_USER')")
    public @ResponseBody Iterable<Tool> getAvailableTools() {
        // This returns a JSON or XML with the users
        return toolRepository.findByIsAvailable(true);
    }
}
