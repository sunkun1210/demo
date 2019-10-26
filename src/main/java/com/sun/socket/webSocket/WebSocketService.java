package com.sun.socket.webSocket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
/**
 * WebSocket协议是基于TCP的一种新的网络协议。它实现了浏览器与服务器全双工(full-duplex)通信——允许服务器主动发送信息给客户端。
 * 这样就可以实现客户端发送消息到服务器，而服务器又可以转发消息到客户端
 * 这样就能够实现客户端直接的交互
 * 目前很多浏览器已经实现了WebSocket协议，但是依旧存在很多浏览器没有实现该协议
 *
 */
@ServerEndpoint("/ws")
public class WebSocketService {
	
	//静态变量 用来记录当前在线连接数。应该把它设计成安全的
	private static int onlineCount=0;
	
	//current包的线程安全set，用来存放每个客户端对应的WebSocketService对象
	private static CopyOnWriteArraySet<WebSocketService> webSocketSet =  new CopyOnWriteArraySet<>();
	
	//与某个客户端连接会话，需要通过它来给客户端发送数据
	private Session session;
	
	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session=session;
		webSocketSet.add(this); //加入Set中
		addOnlineCount();	//在线数+1
		System.out.println("有新的连接加入! 当前在线人数为:"+getOnlineCount());
		try {
			sendMessage("有新的连接加入了!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void close() {
		webSocketSet.remove(this); //从set中删除
		subOnlineCount(); //在线数减一
		System.out.println("有一连接关闭 当前在线人数:"+getOnlineCount());
	}
	
	/**
	 * 收到客户端消息后调用的方法 
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message,Session session) {
		System.out.println("来自客户端的消息:"+message);
		for (WebSocketService webSocketService : webSocketSet) {
			try {
				webSocketService.sendMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发生错误时调用
	 */
	@OnError
	public void onError(Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}
	
	/**
	 * 给客户端发送消息
	 * @param message 客户端消息
	 */
	private void sendMessage(String message)throws Exception {
		this.session.getBasicRemote().sendText(message);
	}
	
	//返回在线数
	private static synchronized int getOnlineCount() {
		return onlineCount;
	}
	//当连接人数增加时
	private static synchronized void addOnlineCount() {
		onlineCount++;
	}
	//当连接人数减少时
	private static synchronized void subOnlineCount() {
		onlineCount--;
	}
}
