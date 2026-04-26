package id.colossus.budget.account.core_service.impl;

import id.colossus.budget.account.core_service.AccountService;
import id.colossus.budget.account.facade.exception.BadRequestException;
import id.colossus.budget.account.facade.exception.ResourceNotFoundException;
import id.colossus.budget.account.repository.BudgetAccountRepository;
import id.colossus.budget.account.repository.UserRepository;
import id.colossus.budget.account.repository.entity.BudgetAccount;
import id.colossus.budget.account.repository.entity.User;
import id.colossus.common.lang.dto.AccountResponse;
import id.colossus.common.lang.dto.CreateAccountRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of AccountService for budget account operations.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final BudgetAccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(BudgetAccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public AccountResponse createAccount(Long userId, CreateAccountRequest request) {
        if (accountRepository.existsByAccountNo(request.getAccountNo())) {
            throw new BadRequestException("Account number already exists");
        }

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        BudgetAccount account = new BudgetAccount(
            user,
            request.getAccountName(),
            request.getAccountNo(),
            request.getAccountType(),
            request.getAccountCurrency()
        );
        account.setAccountDescription(request.getAccountDescription());
        account = accountRepository.save(account);

        return AccountResponse.fromEntity(account);
    }

    @Override
    public List<AccountResponse> getAccounts(Long userId) {
        return accountRepository.findByUserIdAndDeletedFalse(userId)
            .stream()
            .map(AccountResponse::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public AccountResponse getAccount(Long userId, Long accountId) {
        BudgetAccount account = accountRepository.findByIdAndUserIdAndDeletedFalse(accountId, userId)
            .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));
        return AccountResponse.fromEntity(account);
    }

    @Override
    @Transactional
    public AccountResponse updateAccount(Long userId, Long accountId, CreateAccountRequest request) {
        BudgetAccount account = accountRepository.findByIdAndUserIdAndDeletedFalse(accountId, userId)
            .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        if (request.getAccountName() != null) {
            account.setAccountName(request.getAccountName());
        }
        if (request.getAccountType() != null) {
            account.setAccountType(request.getAccountType());
        }
        if (request.getAccountDescription() != null) {
            account.setAccountDescription(request.getAccountDescription());
        }
        if (request.getAccountCurrency() != null) {
            account.setAccountCurrency(request.getAccountCurrency());
        }

        account = accountRepository.save(account);
        return AccountResponse.fromEntity(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long userId, Long accountId) {
        BudgetAccount account = accountRepository.findByIdAndUserIdAndDeletedFalse(accountId, userId)
            .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));
        
        account.setDeleted(true);
        accountRepository.save(account);
    }
}