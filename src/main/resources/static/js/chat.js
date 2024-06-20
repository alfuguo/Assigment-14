document.addEventListener('DOMContentLoaded', function() {
    const channelId = document.getElementById('channel-id').value;
    const userId = document.getElementById('user-id').value;
    const messageInput = document.getElementById('message-input');
    const sendButton = document.getElementById('send-button');
    const messagesContainer = document.getElementById('messages');

    let lastMessageId = 0;
    let displayedMessageIds = new Set();

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
        fetch('/channels/' + channelId + '/messages?after=' + lastMessageId)
            .then(response => response.json())
            .then(messages => {
                let hasNewMessages = false;
                messages.forEach(function(message) {
                    if (!displayedMessageIds.has(message.id)) {
                        appendMessage(message);
                        displayedMessageIds.add(message.id);
                        lastMessageId = Math.max(lastMessageId, message.id);
                        hasNewMessages = true;
                    }
                });
                if (hasNewMessages) {
                    messagesContainer.scrollTop = messagesContainer.scrollHeight;
                }
            })
            .catch(error => console.error('Error:', error));
    }

    function appendMessage(message) {
        const messageElement = document.createElement('div');
        messageElement.className = message.user.id === userId ? 'message self' : 'message';
        messageElement.innerHTML = `<strong>${message.user.username}:</strong> ${message.content}`;
        messageElement.style.opacity = '0';
        messagesContainer.appendChild(messageElement);

        // Trigger reflow to ensure the transition applies
        messageElement.offsetHeight;

        messageElement.style.opacity = '1';
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