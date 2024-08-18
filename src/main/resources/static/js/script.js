document.addEventListener('DOMContentLoaded', function () {
    const shortenButton = document.getElementById('shortenButton');
    const urlInput = document.getElementById('urlInput');
    const urlOutput = document.getElementById('urlOutput');
    const copyButton = document.getElementById('copyButton');
    const alertContainer = document.getElementById('alertContainer');


    function showAlert(message, type) {
        const alert = document.createElement('div');

        alert.className = `alert alert-${type} alert-dismissible fade show p-3`;
        alert.role = 'alert';
        alert.innerHTML = message;

        alertContainer.appendChild(alert);

        setTimeout(() => {
            alert.classList.remove('show');
            alert.addEventListener('transitionend', () => alert.remove());
        }, 3000);
    }


    shortenButton.addEventListener('click', function () {
        const url = urlInput.value.trim();

        if (!url) {
            showAlert('Please enter a URL', 'warning');
            return;
        }

        fetch('/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ url: url })
        })
        .then(response => response.text())
        .then(data => {
            if (data.startsWith('Invalid URL format')) {
                showAlert(data, 'danger');
                urlOutput.value = '';
            } else {
                urlOutput.value = data;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showAlert('An error occurred. Please try again later.', 'danger');
            urlOutput.value = '';
        });
    });


    copyButton.addEventListener('click', function () {
        urlOutput.select();
        document.execCommand('copy');
        showAlert('URL copied to clipboard!', 'success');
    });
});