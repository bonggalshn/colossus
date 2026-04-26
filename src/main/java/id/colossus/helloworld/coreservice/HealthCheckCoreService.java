package id.colossus.helloworld.coreservice;

import id.colossus.helloworld.commonlang.ApiResponse;
import id.colossus.helloworld.commonlang.HealthResponse;
import id.colossus.helloworld.commonutil.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * Core service for Health Check API.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@Service
public class HealthCheckCoreService {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckCoreService.class);
    
    private static final long START_TIME_MILLIS = System.currentTimeMillis();

    /**
     * Gets the health status of the application.
     *
     * @return ApiResponse containing health information
     */
    public ApiResponse getHealthStatus() {
        logger.debug("Getting health status from core service");
        
        long uptimeMillis = System.currentTimeMillis() - START_TIME_MILLIS;
        
        HealthResponse health = new HealthResponse();
        health.setMessage("API is healthy");
        health.setStatus(Constants.STATUS_SUCCESS);
        health.setServiceStatus(Constants.HEALTH_STATUS_HEALTHY);
        health.setVersion(Constants.API_VERSION);
        health.setUptime(Duration.ofMillis(uptimeMillis));
        
        return ApiResponse.success("Health check successful", health);
    }
}