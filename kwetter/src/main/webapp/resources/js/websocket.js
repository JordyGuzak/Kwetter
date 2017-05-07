/**
 * Created by jordy on 05/07/2017.
 */

var webSocket;

$(document).ready(function() {
    init();
});

function onOpen(event) {
    writeToOutput('CONNECTED!');
}

function onClose(event) {
    writeToOutput('DISCONNECTED!');
}

function onMessage(event) {
    writeToOutput('RECEIVED MESSAGE...')
    console.log(event);
    var data = JSON.parse(event.data);
    writeToTimeline(data);
    writeToOutput(data.username + ': ' + data.message);
}

function writeToOutput(message) {
    console.log(message);
    $('#output').append(message + '\n');
}

function writeToTimeline(data) {
    $('#timeline').prepend('' +
        '<div class="panel panel-default">' +
            '<div class="panel-heading">' +
                data.username +
                '<span style="float:right;">' + data.postDate +'</span>' +
            '</div>' +
            '<div class="panel-body">' + data.message + '</div>' +
        '</div>');
}

function onPostKweet(username, kweetMessage) {
    writeToOutput('SENDING MESSAGE...');

    var message = {
        'action' : 'postKweet',
        'username' : username,
        'message' : kweetMessage
    }

    webSocket.send(JSON.stringify(message));
}

function init() {
    writeToOutput('INITIALIZING...');
    webSocket = new WebSocket('ws://localhost:8080/kwetter/websocket');
    webSocket.onopen = onOpen;
    webSocket.onclose = onClose;
    webSocket.onmessage = onMessage;
}

