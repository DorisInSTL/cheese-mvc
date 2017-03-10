package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * Created by Andrew Bell on 3/8/17.
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

    // Request path: GET /cheese
    // Returns index of cheeses
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    // Request path: GET /cheese/add
    // Returns add cheese form
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    // Request path: POST /cheese/add
    // Adds cheese
    // Redirects to index
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String description) {
        cheeses.put(cheeseName, description);
        return "redirect:";
    }

    // Request path: GET /cheese/delete
    // Returns delete cheese form
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Delete Cheese");
        return "cheese/delete";
    }

    // Request path: GET /cheese/delete/{cheesename}
    // Using PathVariable ended up being much cleaner than what I was trying to accomplish in class
    // Deletes the specified cheese
    // Redirects to index
    @RequestMapping(value = "delete/{cheese}", method = RequestMethod.GET)
    public String processDeleteCheese(@PathVariable String cheese) {
        cheeses.remove(cheese);
        // Redirect to /cheese
        return "redirect:";
    }


}
