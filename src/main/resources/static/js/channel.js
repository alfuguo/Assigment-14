document.addEventListener('DOMContentLoaded', function() {
    var channelId = /*[[${channel.id}]]*/ '';
    var sendButton = document.getElementById('send-button');
    var messageInput = document.getElementById('message-input');
    var messagesDiv = document.getElementById('messages');

    sendButton.addEventListener('click', sendMessage);
    setInterval(getMessages, 500);

    function sendMessage() {
        var messageContent = messageInput.value;
        var formData = new FormData();
        formData.append('messageContent', messageContent);

        fetch('/channels/' + channelId + '/send', {
            method: 'POST',
            body: formData
        })
            .then(function() {
                messageInput.value = '';
            })
            .catch(function(error) {
                console.error('Error sending message:', error);
            });
    }

    function getMessages() {
        fetch('/channels/' + channelId + '/messages')
            .then(function(response) {
                return response.json();
            })
            .then(function(messages) {
                var messagesHtml = '';
                for (var i = 0; i < messages.length; i++) {
                    var message = messages[i];
                    messagesHtml += '<div>' + message.sender.name + ': ' + message.content + '</div>';
                }
                messagesDiv.innerHTML = messagesHtml;
            })
            .catch(function(error) {
                console.error('Error retrieving messages:', error);
            });
    }
});