package com.sun.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Connector类负责建立与远程服务器的连接，以及接收和发送Socket对象
 */
public class Connector {

	private String host;
	private int port;
	private Socket sk1;
	private InputStream is;
	private ObjectOutputStream oos;
	private OutputStream os;
	private ObjectInputStream ois;
	
	public Connector(String host,int port) throws Exception {
		this.host=host;
		this.port=port;
		connect(host,port);
	}
	
	public void send(Object obj)throws Exception { //发送对象
		oos.writeObject(obj);
	}
	
	public Object receive() throws Exception { //接收对象
		return ois.readObject();
	}
	
	public void connect(String host,int port) throws Exception {//建立与远程服务器的连接
		sk1=new Socket(host,port);
		os=sk1.getOutputStream();
		oos=new ObjectOutputStream(os);
		is=sk1.getInputStream();
		ois=new ObjectInputStream(is);
	}
	
	public void close() throws Exception  { //关闭连接
		ois.close();
		oos.close();
		sk1.close();
	}
	
}
