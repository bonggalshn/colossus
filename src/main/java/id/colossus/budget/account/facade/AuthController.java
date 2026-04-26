package id.colossus.budget.account.facade;

import id.colossus.budget.account.core_service.UserService;
import id.colossus.budget.account.facade.exception.BadRequestException;
import id.colossus.common.lang.dto.AuthResponse;
import id.colossus.common.lang.dto.LoginRequest;
import id.colossus.common.lang.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for authentication endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register a new user.
     * @param request Registration request
     * @return AuthResponse
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Login user.
     * @param request Login request
     * @return AuthResponse
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Handle bad request exceptions.
     * @param ex Exception
     * @return Error response
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<AuthResponse> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.badRequest().body(AuthResponse.error(ex.getMessage()));
    }
}