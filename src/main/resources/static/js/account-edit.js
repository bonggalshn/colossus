/**
 * Account edit form handling.
 */
document.addEventListener('DOMContentLoaded', async function() {
    const accountId = document.getElementById('accountId').value;
    const form = document.getElementById('editForm');
    const messageDiv = document.getElementById('message');

    try {
        const response = await fetch('/api/v1/accounts/' + accountId, {
            credentials: 'same-origin'
        });
        
        if (!response.ok) {
            throw new Error('Failed to load account');
        }
        
        const account = await response.json();
        
        document.getElementById('accountName').value = account.accountName;
        document.getElementById('accountType').value = account.accountType;
        document.getElementById('accountCurrency').value = account.accountCurrency;
        document.getElementById('accountDescription').value = account.accountDescription || '';
        
    } catch (error) {
        messageDiv.className = 'error';
        messageDiv.textContent = 'Error loading account: ' + error.message;
    }

    if (form) {
        form.addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const formData = {
                accountName: document.getElementById('accountName').value,
                accountType: document.getElementById('accountType').value,
                accountCurrency: document.getElementById('accountCurrency').value,
                accountDescription: document.getElementById('accountDescription').value
            };

            try {
                const response = await fetch('/api/v1/accounts/' + accountId, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(formData),
                    credentials: 'same-origin'
                });
                
                if (response.ok) {
                    messageDiv.className = 'success';
                    messageDiv.textContent = 'Account updated successfully!';
                    setTimeout(() => window.location.href = '/accounts', 1500);
                } else {
                    const error = await response.text();
                    messageDiv.className = 'error';
                    messageDiv.textContent = error || 'Failed to update account';
                }
            } catch (error) {
                messageDiv.className = 'error';
                messageDiv.textContent = 'An error occurred. Please try again.';
            }
        });
    }
});