/**
 * Authentication form handling for register and login pages.
 */

document.addEventListener('DOMContentLoaded', function() {
    const registerForm = document.getElementById('registerForm');
    const loginForm = document.getElementById('loginForm');
    const messageDiv = document.getElementById('message');

    if (registerForm) {
        registerForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            const formData = {
                username: document.getElementById('username').value,
                password: document.getElementById('password').value
            };

            try {
                const response = await fetch('/api/v1/auth/register', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(formData)
                });
                const result = await response.json();
                if (result.message && result.message.includes('successful')) {
                    messageDiv.className = 'success';
                    messageDiv.textContent = result.message + ' Redirecting to login...';
                    setTimeout(() => window.location.href = '/login', 1500);
                } else {
                    messageDiv.className = 'error';
                    messageDiv.textContent = result.message || 'Registration failed';
                }
            } catch (error) {
                messageDiv.className = 'error';
                messageDiv.textContent = 'An error occurred. Please try again.';
            }
        });
    }

    if (loginForm) {
        loginForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            const formData = {
                username: document.getElementById('username').value,
                password: document.getElementById('password').value
            };

            try {
                const response = await fetch('/api/v1/auth/login', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(formData)
                });
                const result = await response.json();
                if (result.message && result.message.includes('successful')) {
                    messageDiv.className = 'success';
                    messageDiv.textContent = 'Login successful! Redirecting...';
                    setTimeout(() => window.location.href = '/accounts', 1000);
                } else {
                    messageDiv.className = 'error';
                    messageDiv.textContent = result.message || 'Invalid credentials';
                }
            } catch (error) {
                messageDiv.className = 'error';
                messageDiv.textContent = 'An error occurred. Please try again.';
            }
        });
    }
});