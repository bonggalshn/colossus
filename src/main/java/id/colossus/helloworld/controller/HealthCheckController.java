package id.colossus.helloworld.controller;

import id.colossus.helloworld.commonutil.Constants;
import id.colossus.helloworld.facade.HealthCheckFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for Health Check API endpoint.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@RestController
@RequestMapping(Constants.API_BASE_PATH)
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);
    
    private final HealthCheckFacade facade;

    public HealthCheckController(HealthCheckFacade facade) {
        this.facade = facade;
    }

    /**
     * Returns health status of the application.
     * 
     * @return JSON response with health status and version
     */
    @GetMapping(Constants.HEALTH_ENDPOINT)
    public ResponseEntity<?> healthCheck() {
        logger.info("Received GET request to /api/health");
        
        var response = facade.getHealthStatus();
        
        return ResponseEntity.ok(response);
    }
}