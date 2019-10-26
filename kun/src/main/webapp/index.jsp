<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"//"+request.getServerName()+":"+request.getServerPort();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <base href="<%=basePath %>"> --%>
<%System.out.println("---"+basePath); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Web Socket</title>
<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/websocket.js"></script>
</head>
<body>
	测试一下websocket站点吧
	<br/>
	<input id="message" type="text"/>
	<button onclick="sendMessage()">发送消息</button>
	<button onclick="closeWebSockt()">关闭socket连接</button>
	<div id="context"></div>
</body>
</html>