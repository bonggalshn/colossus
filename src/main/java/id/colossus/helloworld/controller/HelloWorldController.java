package id.colossus.helloworld.controller;

import id.colossus.helloworld.commonutil.Constants;
import id.colossus.helloworld.facade.HelloWorldFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for Hello World API endpoints.
 * Handles HTTP requests and delegates to the facade layer.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@RestController
@RequestMapping(Constants.API_BASE_PATH)
public class HelloWorldController {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    
    private final HelloWorldFacade facade;

    public HelloWorldController(HelloWorldFacade facade) {
        this.facade = facade;
    }

    /**
     * Returns a Hello World greeting.
     * 
     * @param name Optional name parameter for personalized greeting
     * @return JSON response with greeting message
     */
    @GetMapping(Constants.HELLO_ENDPOINT)
    public ResponseEntity<?> helloWorld(
            @RequestParam(value = "name", required = false) String name) {
        
        logger.info("Received GET request to /api/hello with name: {}", name);
        
        var response = facade.helloWorld(name);
        
        return ResponseEntity.ok(response);
    }
}