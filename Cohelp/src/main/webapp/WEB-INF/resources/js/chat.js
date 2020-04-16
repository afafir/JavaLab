function sendMessage( senderId, sender, messsage, taskId) {
    let body = {
        senderId: senderId,
        sender: sender,
        message: messsage,
        taskId: taskId
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {

        }
    });
}

// LONG POLLING
function receiveMessage(taskId) {
    $.ajax({
        url: "/messages?taskId=" + taskId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').append('<li>' +response[0]['sender']+" "+response[0]['message'] + '</li>');
            receiveMessage(taskId);
        }
    })
}

function getAllMessages(taskId) {
    $.ajax({
        url: "/allmessages?taskId="+taskId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            let html = "";
            response.forEach(function (messageDto) {
                html += '<li>'+messageDto['sender']+" " + messageDto['message'] + '</li>';
            });
            $('#messages').html(html);
            receiveMessage(taskId);
        }
    })
}