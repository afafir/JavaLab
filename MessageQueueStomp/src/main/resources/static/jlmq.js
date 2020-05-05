var stomp;

function connect() {
    var webSocket = new SockJS("http://localhost:666/jlmq");
    stomp = Stomp.over(webSocket);
    stomp.connect({}, function (frame) {
        console.log('Connected: ' + frame);
    });
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
    stomp.send("/app/task/"+message.queueName+"/send",{},JSON.stringify(message));
}

function subscribe() {
    stomp.subscribe('/queue/'+document.getElementById('queue_name').value, function (message) {
       showMessageOutput(JSON.parse(message.body))
    });
}
function showMessageOutput(message) {
    if(message.command === 'accepted' || message.command === 'received') {
        $('#tasks').first().after("<p>Your task" + message.messageId + message.command+ "</p>");
    } else if (message.command === 'completed') {
        $('#completed_tasks').first().after("<li>Task in " + message.queueName + " queue with id " + message.messageId + " is completed</li>");
    }
}