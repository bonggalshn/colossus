package id.colossus.helloworld.coreservice;

import id.colossus.helloworld.commonlang.ApiResponse;
import id.colossus.helloworld.commonutil.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Core service for Hello World API containing business logic.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@Service
public class HelloWorldCoreService {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldCoreService.class);

    /**
     * Creates a basic Hello World response message.
     *
     * @param name Optional name parameter for personalized greeting
     * @return ApiResponse with the greeting message
     */
    public ApiResponse createGreeting(String name) {
        logger.debug("Creating greeting with name: {}", name);
        
        String message;
        if (name == null || name.isBlank()) {
            message = "Hello " + Constants.DEFAULT_NAME;
        } else {
            message = "Hello " + name;
        }
        
        logger.debug("Generated message: {}", message);
        return ApiResponse.success(message);
    }

    /**
     * Validates the name parameter.
     *
     * @param name The name to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return true; // Optional parameter
        }
        
        if (name.length() > Constants.MAX_NAME_LENGTH) {
            logger.warn("Name exceeds max length: {} > {}", name.length(), Constants.MAX_NAME_LENGTH);
            return false;
        }
        
        return true;
    }
}