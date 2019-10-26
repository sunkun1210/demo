package com.sun.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.sun.reflect.Call;
/**
 * 负责创建代理类及其实列
 */
public class ProxyFactory {
	public static Object getProxy(final Class classType,final String host, final int port) {
		InvocationHandler handler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Connector connector =null;
				try {
					connector = new Connector(host, port);
					Call call = new Call(classType.getName(),method.getName(),method.getParameterTypes(),args);
					connector.send(call);
					call=(Call) connector.receive();
					Object result = call.getResult();
					if(result instanceof Throwable) {
						 throw new RuntimeException("错误。。。。。。");
					}else {
						return result;
					}
				} finally {
					if(connector!=null) {
						connector.close();
					}
				}
			}
		};
		return Proxy.newProxyInstance(classType.getClassLoader(), new Class[] {classType}, handler);
	}
}
