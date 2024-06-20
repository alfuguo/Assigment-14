$(document).ready(function() {
    var channelId = $('#channel-id').val();
    var userId = $('#user-id').val();

    function sendMessage() {
        var content = $('#message-input').val();
        if (content.trim() !== '') {
            $.ajax({
                url: '/channels/' + channelId + '/messages',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ content: content }),
                success: function() {
                    $('#message-input').val('');
                    getMessages(); // Refresh messages immediately after sending
                }
            });
        }
    }

    function getMessages() {
        $.get('/channels/' + channelId + '/messages', function(messages) {
            var messagesHtml = '';
            messages.forEach(function(message) {
                messagesHtml += '<p><strong>' + message.user.username + ':</strong> ' + message.content + '</p>';
            });
            $('#messages').html(messagesHtml);
        });
    }

    $('#message-input').keypress(function(e) {
        if (e.which == 13 && !e.shiftKey) {
            e.preventDefault();
            sendMessage();
        }
    });

    $('#send-button').click(function() {
        sendMessage();
    });


    getMessages();

    // Poll for new messages every 2 seconds
    setInterval(getMessages, 2000);
});