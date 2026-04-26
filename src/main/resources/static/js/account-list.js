/**
 * Account list management with API integration.
 */
async function loadAccounts() {
    const listDiv = document.getElementById('accountsList');
    
    try {
        const response = await fetch('/api/v1/accounts', {
            credentials: 'same-origin'
        });
        
        if (!response.ok) {
            if (response.status === 401) {
                window.location.href = '/login';
                return;
            }
            throw new Error('Failed to load accounts');
        }
        
        const accounts = await response.json();
        
        if (accounts.length === 0) {
            listDiv.innerHTML = '<p class="empty">No accounts yet. Create your first account!</p>';
            return;
        }
        
        let html = '<table><thead><tr><th>Name</th><th>Number</th><th>Type</th><th>Currency</th><th>Actions</th></tr></thead><tbody>';
        
        accounts.forEach(account => {
            html += '<tr>';
            html += '<td>' + escapeHtml(account.accountName) + '</td>';
            html += '<td>' + escapeHtml(account.accountNo) + '</td>';
            html += '<td>' + account.accountType + '</td>';
            html += '<td>' + account.accountCurrency + '</td>';
            html += '<td>';
            html += '<a href="/accounts/' + account.id + '/edit" class="btn btn-edit">Edit</a> ';
            html += '<button class="btn btn-delete" onclick="deleteAccount(' + account.id + ')">Delete</button>';
            html += '</td>';
            html += '</tr>';
        });
        
        html += '</tbody></table>';
        listDiv.innerHTML = html;
        
    } catch (error) {
        listDiv.innerHTML = '<p class="empty">Error loading accounts: ' + error.message + '</p>';
    }
}

async function deleteAccount(id) {
    if (!confirm('Are you sure you want to delete this account?')) {
        return;
    }
    
    try {
        const response = await fetch('/api/v1/accounts/' + id, {
            method: 'DELETE',
            credentials: 'same-origin'
        });
        
        if (response.ok) {
            loadAccounts();
        } else {
            alert('Failed to delete account');
        }
    } catch (error) {
        alert('Error deleting account: ' + error.message);
    }
}

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

document.addEventListener('DOMContentLoaded', loadAccounts);