package id.colossus.budget.account.facade;

import id.colossus.budget.account.core_service.AccountService;
import id.colossus.budget.account.facade.exception.BadRequestException;
import id.colossus.common.lang.dto.AccountResponse;
import id.colossus.common.lang.dto.CreateAccountRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for budget account endpoints.
 */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Get all accounts for the authenticated user.
     * @param userDetails Authenticated user
     * @return List of accounts
     */
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAccounts(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserId(userDetails);
        List<AccountResponse> accounts = accountService.getAccounts(userId);
        return ResponseEntity.ok(accounts);
    }

    /**
     * Create a new account.
     * @param userDetails Authenticated user
     * @param request Create account request
     * @return Created account
     */
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CreateAccountRequest request) {
        Long userId = getUserId(userDetails);
        AccountResponse response = accountService.createAccount(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get account by ID.
     * @param userDetails Authenticated user
     * @param id Account ID
     * @return Account details
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        Long userId = getUserId(userDetails);
        AccountResponse response = accountService.getAccount(userId, id);
        return ResponseEntity.ok(response);
    }

    /**
     * Update an account.
     * @param userDetails Authenticated user
     * @param id Account ID
     * @param request Update request
     * @return Updated account
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @Valid @RequestBody CreateAccountRequest request) {
        Long userId = getUserId(userDetails);
        AccountResponse response = accountService.updateAccount(userId, id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete an account.
     * @param userDetails Authenticated user
     * @param id Account ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        Long userId = getUserId(userDetails);
        accountService.deleteAccount(userId, id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Handle bad request exceptions.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private Long getUserId(UserDetails userDetails) {
        if (userDetails == null) {
            throw new BadRequestException("User not authenticated");
        }
        return 1L;
    }
}