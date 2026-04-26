package id.colossus.budget.account.repository;

import id.colossus.budget.account.repository.entity.BudgetAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for BudgetAccount entity operations.
 */
@Repository
public interface BudgetAccountRepository extends JpaRepository<BudgetAccount, Long> {

    /**
     * Find all non-deleted accounts for a specific user.
     * @param userId The user ID
     * @return List of budget accounts
     */
    @Query("SELECT ba FROM BudgetAccount ba WHERE ba.user.id = :userId AND ba.deleted = false")
    List<BudgetAccount> findByUserIdAndDeletedFalse(@Param("userId") Long userId);

    /**
     * Find a specific account by ID and user (with soft delete check).
     * @param id The account ID
     * @param userId The user ID
     * @return Optional containing the account if found
     */
    @Query("SELECT ba FROM BudgetAccount ba WHERE ba.id = :id AND ba.user.id = :userId AND ba.deleted = false")
    Optional<BudgetAccount> findByIdAndUserIdAndDeletedFalse(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * Check if account number exists.
     * @param accountNo The account number to check
     * @return true if account number exists
     */
    boolean existsByAccountNo(String accountNo);

    /**
     * Check if account number exists excluding a specific account.
     * @param accountNo The account number
     * @param excludeId Account ID to exclude from check
     * @return true if account number exists
     */
    @Query("SELECT CASE WHEN COUNT(ba) > 0 THEN true ELSE false END FROM BudgetAccount ba WHERE ba.accountNo = :accountNo AND ba.id <> :excludeId")
    boolean existsByAccountNoAndIdNot(@Param("accountNo") String accountNo, @Param("excludeId") Long excludeId);
}