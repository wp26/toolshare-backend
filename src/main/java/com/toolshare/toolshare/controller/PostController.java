package com.toolshare.toolshare.controller;

import com.toolshare.toolshare.entity.Post;
import com.toolshare.toolshare.entity.User;
import com.toolshare.toolshare.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/api/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    //
    // POST-REQUESTS
    //

    @PostMapping(path="/add")
    public @ResponseBody String addNewPost (@RequestParam String title,
                                            @RequestParam String description,
                                            @RequestParam User user_id) {

        Post n = new Post();
        n.setTitle(title);
        n.setDescription(description);
        n.setUser(user_id);

        try {
            postRepository.save(n);
            System.out.println("Post created: " + n.toString());
            return "Post created";
        }
        catch (IllegalArgumentException exception) {
            return exception.toString();
        }
    }

    @PostMapping(path="/delete")
    public @ResponseBody  String deleteUserByID(@RequestParam int id) {

        postRepository.deleteById(id);

        return "Deleted";
    }

    //
    // GET-REQUESTS
    //


    @GetMapping(path="/all")
    public @ResponseBody Iterable<Post> getAllPosts() {
        // This returns a JSON or XML with the users
        return postRepository.findAll();
    }

    @GetMapping(path="/user")
    public @ResponseBody Iterable<Post> getUserPosts(@RequestParam User user_id) {
        return postRepository.findByUser(user_id);
    }
}