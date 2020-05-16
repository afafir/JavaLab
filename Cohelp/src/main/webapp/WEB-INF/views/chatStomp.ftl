<!DOCTYPE html>
<#assign  security=JspTaglibs["/WEB-INF/security.tld"] />
<@security.authorize access=" isAuthenticated()"/>
<@security.authentication var="user" property="principal.user" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-grid.css">
    <link rel="stylesheet" href=/resources/css/bootstrap-reboot.css">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script>
        var stompClient = null;
        function connect() {
            var socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/task/${task.id}', function (messageOutput) {
                    showMessageOutput(JSON.parse(messageOutput.body));
                });
            });
        }


        function sendMessage(text) {
            var senderId = '${user.id}';
            var sender = '${user.name}';
            var message = text;
            var taskId = '${task.id}';
            stompClient.send("/app/chat/${task.id}/sendMessage", {},
                JSON.stringify({'senderId': senderId, 'sender': sender, 'message': message, 'taskId': taskId}));
        }

        function showMessageOutput(messageOutput) {
            var response = document.getElementById('messages');
            var li = document.createElement('li');
            li.className="d-flex justify-content-between mb-4";
            li.innerHTML="<li class=\"d-flex justify-content-between mb-4\">\n" +
                "                                <div class=\"chat-body white p-3 ml-2 z-depth-1\">\n" +
                "                                    <div class=\"header\">\n" +
                "                                        <strong class=\"primary-font\">" + messageOutput.sender + "</strong>\n" +
                "                                    </div>\n" +
                "                                    <hr class=\"w-100\">\n" +
                "                                    <p class=\"mb-0\">\n" +
                                     messageOutput.message+"\n" +
                "                                    </p>\n" +
                "                                </div>\n" +
                "                            </li>";
            response.appendChild(li);
        }
    </script>
</head>
<#include "header.ftl">
<body onload="connect()">


<div>
    <div class="card grey lighten-3 chat-room">
        <div class="card-body">
            <div class="row px-lg-2 px-2">
                <div class="col-md-6 col-xl-8 pl-md-3 px-lg-auto px-0">
                    <div class="chat-message">
                        <ul id="messages" class="list-unstyled chat">
                            <#list messages as message>
                            <#if message.senderId != user.id>
                            <li class="d-flex justify-content-between mb-4">
                                <div class="chat-body white p-3 ml-2 z-depth-1">
                                    <div class="header">
                                        <strong class="primary-font">${message.sender}</strong>
                                    </div>
                                    <hr class="w-100">
                                    <p class="mb-0">
                                        ${message.message}
                                    </p>
                                </div>
                            </li>
                            </#if>
                            <#if message.senderId == user.id>
                            <li class="d-flex justify-content-between mb-4">
                                <div class="chat-body white p-3 z-depth-1">
                                    <div class="header">
                                        <strong class="primary-font">${message.sender}</strong>
                                    </div>
                                    <hr class="w-100">
                                    <p class="mb-0">
                                        ${message.message}
                                    </p>
                                </div>
                            </li>
                            </#if>
                            </#list>
                        </ul>
                            <ul id="send" class="list-unstyled chat">
                            <li class="white">
                                <div class="form-group basic-textarea">
                                    <input class="form-control pl-2 my-0" id="text" rows="3" placeholder="Type your message here...">
                                </div>
                            </li><button onclick="sendMessage($('#text').val())" type="button" class="btn btn-info btn-rounded btn-sm waves-effect waves-light float-right">Send</button>
                            </ul>



                    </div>

                </div>
                <!-- Grid column -->

            </div>
            <!-- Grid row -->

        </div>
    </div>









    <ul id="messages">
        <#list messages as message>
            <li> ${message.sender}: ${message.message}</li>
        </#list>

    </ul>
</div>
</body>
</html>