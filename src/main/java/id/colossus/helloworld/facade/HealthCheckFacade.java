package id.colossus.helloworld.facade;

import id.colossus.helloworld.commonlang.ApiResponse;
import id.colossus.helloworld.coreservice.HealthCheckCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Facade layer for Health Check API.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@Component
public class HealthCheckFacade {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckFacade.class);
    
    private final HealthCheckCoreService coreService;

    public HealthCheckFacade(HealthCheckCoreService coreService) {
        this.coreService = coreService;
    }

    /**
     * Gets the health status of the application.
     *
     * @return ApiResponse containing health information
     */
    public ApiResponse getHealthStatus() {
        logger.debug("Getting health status");
        return coreService.getHealthStatus();
    }
}