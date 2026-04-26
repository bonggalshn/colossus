package id.colossus.helloworld.facade;

import id.colossus.helloworld.bizservice.HelloWorldBizService;
import id.colossus.helloworld.commonlang.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Facade layer for Hello World API.
 * Acts as the main interface between controller and business services.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@Component
public class HelloWorldFacade {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldFacade.class);
    
    private final HelloWorldBizService bizService;

    public HelloWorldFacade(HelloWorldBizService bizService) {
        this.bizService = bizService;
    }

    /**
     * Retrieves the greeting message.
     *
     * @param name Optional name for personalized greeting
     * @return ApiResponse containing the greeting
     */
    public ApiResponse helloWorld(String name) {
        logger.debug("Facade received request with name: {}", name);
        
        if (name == null || name.isBlank()) {
            return bizService.getDefaultGreeting();
        }
        
        return bizService.getGreeting(name);
    }
}