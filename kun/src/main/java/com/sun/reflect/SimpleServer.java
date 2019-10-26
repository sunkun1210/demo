package com.sun.reflect;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SimpleServer {
	private Map<String,Object> remoteObjects = new HashMap<>();
	
	/**把一个远程对象放到缓存中*/
	public void register(String className,Object remoteObject) {
		remoteObjects.put(className, remoteObject);
	}
	
	public void service() throws Exception{
		ServerSocket serverSocket = new ServerSocket(8000);
		System.out.println("服务启动");
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("开始连接");
			InputStream in = socket.getInputStream();
			ObjectInputStream ois=new ObjectInputStream(in);
			OutputStream out = socket.getOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(out);
			Call call = (Call) ois.readObject();//接收客户端发送的call对象
			System.out.println(call);
			call=invoke(call);   //调用相关对象的方法
			oos.writeObject(call);//向客户端发送包含执行结果的Call对象
			while(ois.available()!=0) {
				ois.close();
				oos.close();
				socket.close();
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Call invoke(Call call) throws Exception {
		Object result = null;
		String className = call.getClassName();
		String methodName=call.getMethodName();
		Object[] params = call.getParams();
		Class classType=Class.forName(className);
		Class[] parameterTypes=call.getParamTypes();
		Method method = classType.getMethod(methodName, parameterTypes);
		Object remoteObject = remoteObjects.get(className); //从缓存中取出相关的远程对象
		result = method.invoke(remoteObject, params);
		call.setResult(result);
		return call;
	}
	public static void main(String[] args) throws Exception {
		SimpleServer server = new SimpleServer();
		server.register("com.sun.reflect.HelloService", new HelloServiceImpl());
		server.service();
	}
}
