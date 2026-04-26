package id.colossus.budget.account.core_service;

import id.colossus.common.lang.dto.AuthResponse;
import id.colossus.common.lang.dto.LoginRequest;
import id.colossus.common.lang.dto.RegisterRequest;

/**
 * Service interface for user authentication operations.
 */
public interface UserService {

    /**
     * Register a new user.
     * @param request Registration request
     * @return AuthResponse with user details
     */
    AuthResponse register(RegisterRequest request);

    /**
     * Authenticate user login.
     * @param request Login request
     * @return AuthResponse with user details
     */
    AuthResponse login(LoginRequest request);
}