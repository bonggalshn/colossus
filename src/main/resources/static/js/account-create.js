/**
 * Account creation form handling.
 */
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('accountForm');
    const messageDiv = document.getElementById('message');

    if (form) {
        form.addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const formData = {
                accountName: document.getElementById('accountName').value,
                accountNo: document.getElementById('accountNo').value,
                accountType: document.getElementById('accountType').value,
                accountCurrency: document.getElementById('accountCurrency').value.toUpperCase(),
                accountDescription: document.getElementById('accountDescription').value
            };

            try {
                const response = await fetch('/api/v1/accounts', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(formData),
                    credentials: 'same-origin'
                });
                
                if (response.ok) {
                    const result = await response.json();
                    messageDiv.className = 'success';
                    messageDiv.textContent = 'Account created successfully!';
                    setTimeout(() => window.location.href = '/accounts', 1500);
                } else {
                    const error = await response.text();
                    messageDiv.className = 'error';
                    messageDiv.textContent = error || 'Failed to create account';
                }
            } catch (error) {
                messageDiv.className = 'error';
                messageDiv.textContent = 'An error occurred. Please try again.';
            }
        });
    }
});