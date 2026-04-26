package id.colossus.budget.account.core_service.impl;

import id.colossus.budget.account.core_service.UserService;
import id.colossus.budget.account.facade.exception.BadRequestException;
import id.colossus.budget.account.repository.UserRepository;
import id.colossus.budget.account.repository.entity.User;
import id.colossus.common.lang.dto.AuthResponse;
import id.colossus.common.lang.dto.LoginRequest;
import id.colossus.common.lang.dto.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of UserService for authentication operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);

        return AuthResponse.success(user.getId(), user.getUsername(), "Registration successful");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new BadRequestException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid username or password");
        }

        return AuthResponse.success(user.getId(), user.getUsername(), "Login successful");
    }
}