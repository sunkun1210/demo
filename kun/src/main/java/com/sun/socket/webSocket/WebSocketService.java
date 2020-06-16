package com.sun.socket.webSocket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
/**
 * WebSocketЭ���ǻ���TCP��һ���µ�����Э�顣��ʵ����������������ȫ˫��(full-duplex)ͨ�š����������������������Ϣ���ͻ��ˡ�
 * �����Ϳ���ʵ�ֿͻ��˷�����Ϣ�������������������ֿ���ת����Ϣ���ͻ���
 * �������ܹ�ʵ�ֿͻ���ֱ�ӵĽ���
 * Ŀǰ�ܶ�������Ѿ�ʵ����WebSocketЭ�飬�������ɴ��ںܶ������û��ʵ�ָ�Э��
 *
 */
@ServerEndpoint("/ws")
public class WebSocketService {
	
	//��̬���� ������¼��ǰ������������Ӧ�ð�����Ƴɰ�ȫ��
	private static int onlineCount=0;
	
	//current�����̰߳�ȫset���������ÿ���ͻ��˶�Ӧ��WebSocketService����
	private static CopyOnWriteArraySet<WebSocketService> webSocketSet =  new CopyOnWriteArraySet<>();
	
	//��ĳ���ͻ������ӻỰ����Ҫͨ���������ͻ��˷�������
	private Session session;
	
	/**
	 * ���ӽ����ɹ����õķ���
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session=session;
		webSocketSet.add(this); //����Set��
		addOnlineCount();	//������+1
		System.out.println("���µ����Ӽ���! ��ǰ��������Ϊ:"+getOnlineCount());
		try {
			sendMessage("���µ����Ӽ�����!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ���ӹرյ��õķ���
	 */
	@OnClose
	public void close() {
		webSocketSet.remove(this); //��set��ɾ��
		subOnlineCount(); //��������һ
		System.out.println("��һ���ӹر� ��ǰ��������:"+getOnlineCount());
	}
	
	/**
	 * �յ��ͻ�����Ϣ����õķ��� 
	 * @param message �ͻ��˷��͹�������Ϣ
	 */
	@OnMessage
	public void onMessage(String message,Session session) {
		System.out.println("���Կͻ��˵���Ϣ:"+message);
		for (WebSocketService webSocketService : webSocketSet) {
			try {
				webSocketService.sendMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��������ʱ����
	 */
	@OnError
	public void onError(Throwable error) {
		System.out.println("��������");
		error.printStackTrace();
	}
	
	/**
	 * ���ͻ��˷�����Ϣ
	 * @param message �ͻ�����Ϣ
	 */
	private void sendMessage(String message)throws Exception {
		this.session.getBasicRemote().sendText(message);
	}
	
	//����������
	private static synchronized int getOnlineCount() {
		return onlineCount;
	}
	//��������������ʱ
	private static synchronized void addOnlineCount() {
		onlineCount++;
	}
	//��������������ʱ
	private static synchronized void subOnlineCount() {
		onlineCount--;
	}
}
