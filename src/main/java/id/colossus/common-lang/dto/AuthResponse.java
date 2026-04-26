package id.colossus.common.lang.dto;

import java.time.LocalDateTime;

/**
 * Response DTO for authentication operations.
 */
public class AuthResponse {

    private Long userId;
    private String username;
    private String message;
    private LocalDateTime timestamp;

    public AuthResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public AuthResponse(Long userId, String username, String message) {
        this.userId = userId;
        this.username = username;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static AuthResponse success(Long userId, String username, String message) {
        return new AuthResponse(userId, username, message);
    }

    public static AuthResponse error(String message) {
        return new AuthResponse(null, null, message);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}