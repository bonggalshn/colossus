package id.colossus.helloworld.commonlang;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

/**
 * Specialized response for health check endpoint.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthResponse {

    private String message;
    private String status;
    private String serviceStatus;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timestamp;
    
    private String version;
    private Map<String, String> dependencies;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration uptime;

    public HealthResponse() {
        this.timestamp = Instant.now();
        this.version = "1.0.0";
    }

    public HealthResponse(String message, String status, String serviceStatus) {
        this();
        this.message = message;
        this.status = status;
        this.serviceStatus = serviceStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Map<String, String> dependencies) {
        this.dependencies = dependencies;
    }

    public Duration getUptime() {
        return uptime;
    }

    public void setUptime(Duration uptime) {
        this.uptime = uptime;
    }

    /**
     * Creates a healthy response.
     *
     * @return HealthResponse indicating healthy status
     */
    public static HealthResponse healthy() {
        HealthResponse response = new HealthResponse();
        response.setMessage("API is healthy");
        response.setStatus("success");
        response.setServiceStatus("healthy");
        response.setDependencies(Map.of(
            "database", "not_applicable",
            "externalServices", "not_applicable"
        ));
        return response;
    }

    /**
     * Creates an unhealthy response.
     *
     * @return HealthResponse indicating unhealthy status
     */
    public static HealthResponse unhealthy() {
        HealthResponse response = new HealthResponse();
        response.setMessage("API is unhealthy");
        response.setStatus("error");
        response.setServiceStatus("unhealthy");
        return response;
    }
}