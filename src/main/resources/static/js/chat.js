document.addEventListener('DOMContentLoaded', function() {
    const channelId = document.getElementById('channel-id').value;
    const userId = document.getElementById('user-id').value;
    const messageInput = document.getElementById('message-input');
    const sendButton = document.getElementById('send-button');
    const messagesContainer = document.getElementById('messages');

    function sendMessage() {
        const content = messageInput.value;
        if (content.trim() !== '') {
            fetch('/channels/' + channelId + '/messages', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ content: content }),
            })
                .then(response => response.json())
                .then(() => {
                    messageInput.value = '';
                    getMessages(); // Refresh messages immediately after sending
                })
                .catch(error => console.error('Error:', error));
        }
    }

    function getMessages() {
        fetch('/channels/' + channelId + '/messages')
            .then(response => response.json())
            .then(messages => {
                let messagesHtml = '';
                messages.forEach(function(message) {
                    messagesHtml += `<p><strong>${message.user.username}:</strong> ${message.content}</p>`;
                });
                messagesContainer.innerHTML = messagesHtml;
            })
            .catch(error => console.error('Error:', error));
    }

    messageInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault();
            sendMessage();
        }
    });

    sendButton.addEventListener('click', sendMessage);

    getMessages();

    // Poll for new messages every 2 seconds
    setInterval(getMessages, 2000);
});