var websocket = new WebSocket("ws://127.0.0.1:8080/ws");

//发送错误的回调方法
websocket.onerror=function(){
	appendMessage("error");
}

//连接成功建立的回调方法
websocket.onopen=function(event){
	appendMessage("open");
}

//接收到消息的回调方法
websocket.onmessage=function(event){
	appendMessage(event.data);
}

//连接关闭的回调方法
websocket.onclose=function(){
	appendMessage("close");
}

/**以上都是回调方法*/

//监听窗口关闭事件，当窗口关闭时，主动关闭webSocket连接
window.onbeforeunload=function(){
	websocket.close();
}

//将消息显示在网页上
function appendMessage(message){
	var context = $("#context").html()+"<br/>"+message
	 $("#context").html(context);
}
//关闭连接
function closeWebSockt(){
	websocket.close();
}
//发送消息
function sendMessage(){
	var message =  $("#message").val();
	websocket.send(message);
}