package id.colossus.helloworld.controller;

import id.colossus.helloworld.commonutil.Constants;
import id.colossus.helloworld.facade.HealthCheckFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Health Check", description = "Health check API endpoints")
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
    @Operation(
            summary = "Get health status",
            description = "Returns the health status of the API"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "API is healthy",
                    content = @Content(schema = @Schema(example = "{\"status\": \"success\"}"))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(Constants.HEALTH_ENDPOINT)
    public ResponseEntity<?> healthCheck() {
        logger.info("Received GET request to /api/health");
        
        var response = facade.getHealthStatus();
        
        return ResponseEntity.ok(response);
    }
}