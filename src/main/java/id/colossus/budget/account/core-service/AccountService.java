package id.colossus.budget.account.core_service;

import id.colossus.common.lang.dto.AccountResponse;
import id.colossus.common.lang.dto.CreateAccountRequest;
import java.util.List;

/**
 * Service interface for budget account operations.
 */
public interface AccountService {

    /**
     * Create a new budget account.
     * @param userId The user ID
     * @param request Create account request
     * @return AccountResponse
     */
    AccountResponse createAccount(Long userId, CreateAccountRequest request);

    /**
     * Get all accounts for a user.
     * @param userId The user ID
     * @return List of accounts
     */
    List<AccountResponse> getAccounts(Long userId);

    /**
     * Get account by ID for a user.
     * @param userId The user ID
     * @param accountId The account ID
     * @return Account response
     */
    AccountResponse getAccount(Long userId, Long accountId);

    /**
     * Update an account.
     * @param userId The user ID
     * @param accountId The account ID
     * @param request Update request
     * @return Updated account
     */
    AccountResponse updateAccount(Long userId, Long accountId, CreateAccountRequest request);

    /**
     * Delete an account (soft delete).
     * @param userId The user ID
     * @param accountId The account ID
     */
    void deleteAccount(Long userId, Long accountId);
}