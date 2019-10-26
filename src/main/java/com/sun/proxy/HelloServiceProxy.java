package com.sun.proxy;

import java.util.Date;

import com.sun.reflect.Call;
import com.sun.reflect.HelloService;

public class HelloServiceProxy implements HelloService{
	private String host;
	private int port;
	
	
	
	public HelloServiceProxy(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
	
	@Override
	public String echo(String msg) throws Exception {
		Connector connector =null;
		try {
			connector = new Connector(host, port);
			Call call = new Call("com.sun.reflect.HelloService","echo",new Class[] {String.class},new Object[] {msg});
			connector.send(call);
			call =(Call) connector.receive();
			Object result = call.getResult();
			return (String) result;
		}finally {
			if(connector!=null) {
				connector.close();
			}
		}
	}
	@Override
	public Date getTime() throws Exception {
		Connector connector =null;
		try {
			connector = new Connector(host, port);
			Call call = new Call("com.sun.reflect.HelloService","getTime",new Class[] {},new Object[] {});
			connector.send(call);
			call =(Call) connector.receive();
			Object result = call.getResult();
			return (Date) result;
		}finally {
			if(connector!=null) {
				connector.close();
			}
		}
	}
}
