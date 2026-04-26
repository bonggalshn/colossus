package id.colossus.helloworld.commonutil;

/**
 * Application-wide constants for the Hello World API.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
public final class Constants {

    private Constants() {
        // Prevent instantiation
    }

    // API Configuration
    public static final String API_VERSION = "1.0.0";
    public static final String API_BASE_PATH = "/api";
    
    // Endpoint Paths
    public static final String HELLO_ENDPOINT = "/hello";
    public static final String HEALTH_ENDPOINT = "/health";
    
    // HTTP Status Codes
    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_NO_CONTENT = 204;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_METHOD_NOT_ALLOWED = 405;
    public static final int HTTP_INTERNAL_ERROR = 500;
    
    // Response Status Values
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_WARNING = "warning";
    
    // Health Status Values
    public static final String HEALTH_STATUS_HEALTHY = "healthy";
    public static final String HEALTH_STATUS_DEGRADED = "degraded";
    public static final String HEALTH_STATUS_UNHEALTHY = "unhealthy";
    
    // Validation Limits
    public static final int MAX_NAME_LENGTH = 100;
    public static final String DEFAULT_NAME = "World";
    
    // Content Types
    public static final String CONTENT_TYPE_JSON = "application/json";
    
    // CORS
    public static final String CORS_ORIGINS_DEV = "http://localhost:3000,http://localhost:8080";
    public static final long CORS_MAX_AGE = 3600L;
}