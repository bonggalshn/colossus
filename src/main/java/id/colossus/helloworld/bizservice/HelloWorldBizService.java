package id.colossus.helloworld.bizservice;

import id.colossus.helloworld.commonlang.ApiResponse;
import id.colossus.helloworld.commonutil.Constants;
import id.colossus.helloworld.coreservice.HelloWorldCoreService;
import id.colossus.helloworld.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Business service layer for Hello World API.
 * Handles business logic and validation before delegating to core service.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@Service
public class HelloWorldBizService {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldBizService.class);
    
    private final HelloWorldCoreService coreService;

    public HelloWorldBizService(HelloWorldCoreService coreService) {
        this.coreService = coreService;
    }

    /**
     * Gets a greeting message with optional personalization.
     *
     * @param name Optional name parameter
     * @return ApiResponse containing the greeting
     * @throws BadRequestException if name validation fails
     */
    public ApiResponse getGreeting(String name) {
        logger.info("Getting greeting for name: {}", name);
        
        // Validate name parameter if provided
        if (!coreService.isValidName(name)) {
            throw new BadRequestException(
                "Name parameter exceeds maximum length of " + Constants.MAX_NAME_LENGTH + " characters"
            );
        }
        
        return coreService.createGreeting(name);
    }

    /**
     * Gets the default Hello World greeting.
     *
     * @return ApiResponse with default greeting
     */
    public ApiResponse getDefaultGreeting() {
        logger.info("Getting default greeting");
        return coreService.createGreeting(null);
    }
}