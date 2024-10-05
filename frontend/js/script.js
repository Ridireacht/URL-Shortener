document.addEventListener('DOMContentLoaded', function () {
    const shortenButton = document.getElementById('shortenButton');
    const urlInput = document.getElementById('urlInput');
    const urlOutput = document.getElementById('urlOutput');
    const copyButton = document.getElementById('copyButton');
    const alertContainer = document.getElementById('alertContainer');
    const qrCodeContainer = document.getElementById('qrCodeContainer');

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

    function displayQRCode(base64Image) {
        const img = document.createElement('img');
        img.src = `data:image/png;base64,${base64Image}`;
        img.classList.add('img-fluid', 'mt-5');
        qrCodeContainer.innerHTML = '';
        qrCodeContainer.appendChild(img);
    }

    shortenButton.addEventListener('click', function () {
        const url = urlInput.value.trim();

        if (!url) {
            showAlert('No URL specified. Enter a URL first', 'warning');
            return;
        }

        fetch('http://localhost:8080/api/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ url: url })
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(errorMessage => {
                    showAlert(errorMessage, 'danger');
                    urlOutput.value = '';
                    qrCodeContainer.innerHTML = '';
                });
            } else {
                return response.json();
            }
        })
        .then(data => {
            if (data) {
                urlOutput.value = data.shortenedUrl;
                displayQRCode(data.qrCodeBase64);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showAlert('An error occurred. Try again later', 'danger');
            urlOutput.value = '';
            qrCodeContainer.innerHTML = '';
        });
    });

    copyButton.addEventListener('click', function () {
        if (urlOutput.value.trim() === '') {
            showAlert('There is no URL to copy. Shorten a URL first', 'warning');
            return;
        }

        urlOutput.select();
        document.execCommand('copy');
        showAlert('URL copied to clipboard!', 'success');
    });
});