package id.colossus.helloworld.commonlang;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

/**
 * Standard API response wrapper for all REST endpoints.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private String message;
    private String status;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timestamp;
    
    private String version;

    public ApiResponse() {
        this.timestamp = Instant.now();
        this.version = "1.0.0";
    }

    public ApiResponse(String message, String status) {
        this();
        this.message = message;
        this.status = status;
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

    /**
     * Creates a successful response.
     *
     * @param message The response message
     * @return ApiResponse with success status
     */
    public static ApiResponse success(String message) {
        return new ApiResponse(message, "success");
    }

    /**
     * Creates an error response.
     *
     * @param message The error message
     * @return ApiResponse with error status
     */
    public static ApiResponse error(String message) {
        return new ApiResponse(message, "error");
    }
}