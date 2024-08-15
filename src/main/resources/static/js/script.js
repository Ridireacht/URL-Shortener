document.addEventListener('DOMContentLoaded', function () {
    const shortenButton = document.getElementById('shortenButton');
    const urlInput = document.getElementById('urlInput');
    const urlOutput = document.getElementById('urlOutput');
    const copyButton = document.getElementById('copyButton');

    shortenButton.addEventListener('click', function () {
        const url = urlInput.value.trim();

        if (!url) {
            alert('Please enter a URL.');
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
                alert(data);
            } else {
                urlOutput.value = data;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred. Please try again later.');
        });
    });

    copyButton.addEventListener('click', function () {
        urlOutput.select();
        document.execCommand('copy');
        alert('URL copied to clipboard!');
    });
});