package id.colossus.common.lang.dto;

import java.time.LocalDateTime;

/**
 * Response DTO for budget account operations.
 */
public class AccountResponse {

    private Long id;
    private String accountName;
    private String accountNo;
    private String accountType;
    private String accountDescription;
    private String accountCurrency;
    private LocalDateTime createdAt;

    public AccountResponse() {
    }

    public static AccountResponse fromEntity(id.colossus.budget.account.repository.entity.BudgetAccount entity) {
        AccountResponse response = new AccountResponse();
        response.setId(entity.getId());
        response.setAccountName(entity.getAccountName());
        response.setAccountNo(entity.getAccountNo());
        response.setAccountType(entity.getAccountType().name());
        response.setAccountDescription(entity.getAccountDescription());
        response.setAccountCurrency(entity.getAccountCurrency());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}