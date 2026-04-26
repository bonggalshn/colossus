package id.colossus.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for serving Thymeleaf templates.
 * Handles web page requests and returns corresponding views.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@Controller
public class HomeController {

    /**
     * Serves the home page.
     * 
     * @return the home Thymeleaf view
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}