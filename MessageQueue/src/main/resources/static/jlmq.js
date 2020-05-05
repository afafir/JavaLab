var webSocket;

function connect() {
    webSocket = new SockJS("http://localhost:8080/queue");
    webSocket.onmessage = function (response) {
        let data = response['data'];
        let message = JSON.parse(data);
        if(message.command === 'accepted' || message.command === 'received') {
            $('#tasks').first().after("<p>Your task" + message.messageId + message.command+ "</p>");
        } else if (message.command === 'completed') {
            $('#completed_tasks').first().after("<li>Task in " + message.queueName + " queue with id " + message.messageId + " is completed</li>");
        }
    };
}
function sendTask() {
    let message = {
        "messageId":"",
        "command":"send",
        "body":{
            "type":"SEND_DOC",
            "email":"afafir123@gmail.com",
        },
        "queueName":document.getElementById('queue_send_name').value
    };
    webSocket.send(JSON.stringify(message));
}

function subscribe() {
    let message = {
        "messageId":"",
        "command":"subscribe",
        "queueName":document.getElementById('queue_name').value
    };
    webSocket.send(JSON.stringify(message));

}