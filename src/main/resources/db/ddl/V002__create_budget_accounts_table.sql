-- V002: Create budget_accounts table for budget account management
-- Database: colossus_database

CREATE TABLE budget_accounts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    account_name VARCHAR(100) NOT NULL,
    account_no VARCHAR(50) NOT NULL UNIQUE,
    account_type VARCHAR(20) NOT NULL CHECK (account_type IN ('CASH', 'DEBIT')),
    account_description VARCHAR(500),
    account_currency VARCHAR(3) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_account_user_id ON budget_accounts(user_id);
CREATE INDEX idx_account_no ON budget_accounts(account_no);
CREATE INDEX idx_account_deleted ON budget_accounts(deleted);

COMMENT ON TABLE budget_accounts IS 'Budget accounts owned by users';
COMMENT ON COLUMN budget_accounts.id IS 'Unique account identifier';
COMMENT ON COLUMN budget_accounts.user_id IS 'Foreign key to users table';
COMMENT ON COLUMN budget_accounts.account_name IS 'Name of the account';
COMMENT ON COLUMN budget_accounts.account_no IS 'Unique account number';
COMMENT ON COLUMN budget_accounts.account_type IS 'Type: CASH or DEBIT';
COMMENT ON COLUMN budget_accounts.account_description IS 'Optional description';
COMMENT ON COLUMN budget_accounts.account_currency IS 'ISO currency code (e.g., USD, EUR)';
COMMENT ON COLUMN budget_accounts.deleted IS 'Soft delete flag';