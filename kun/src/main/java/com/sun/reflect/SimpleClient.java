package com.sun.reflect;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.sun.proxy.HelloServiceProxy;
import com.sun.proxy.ProxyFactory;

public class SimpleClient {
	public void invoke()throws Exception {
		Socket socket = new Socket("127.0.0.1",8000);
		OutputStream out = socket.getOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(out);
		InputStream in = socket.getInputStream();
		ObjectInputStream ois=new ObjectInputStream(in);
		Call call = new Call("com.sun.reflect.HelloService","echo",new Class[] {String.class},new Object[] {"Hello"});
		oos.writeObject(call);
		call=(Call) ois.readObject();
		System.out.println(call.getResult());
		while(ois.available()!=0) {
			ois.close();
			oos.close();
			socket.close();
		}
	}
	public static void main(String[] args)throws Exception {
		//new SimpleClient().invoke();
//		HelloService helloService = (HelloService) ProxyFactory.getProxy(HelloService.class, "127.0.0.1", 8000);
//		System.out.println(helloService.echo("hello"));
//		System.out.println(helloService.getTime());
		HelloService helloService = new HelloServiceProxy("127.0.0.1", 8000);
		System.out.println(helloService.echo("hh"));

	}
}
