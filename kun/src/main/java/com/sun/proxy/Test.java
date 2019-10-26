package com.sun.proxy;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

//public class Test {
//	/**方式一*/
//	//创建InvocationHandler对象
//	InvocationHandler invocationHandler = new InvocationHandler(...);
//	//创建动态代理类
//	Class proxyClass = Proxy.getProxyClass(Subject.class.getClassLoader(), new Class[] {Subject.class});
//	//创建动态代理类的实列
//	Subject subject = (Subject) proxyClass.getConstructor(new Class[] {InvocationHandler.class}).newInstance(new Object[] {invocationHandler});
//	
//	/**方式二*/
//	//创建InvocationHandler对象
//	InvocationHandler invocationHandler = new InvocationHandler(...);
//	//直接创建动态代理类的实列
//	Subject subject = Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[] {Subject.class},invocationHandler);
//	
//	InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("D:\\text.txt"),"UTF-8");
//	char c = (char) inputStreamReader.read();
//	
//	
//	InputStream in1 = new FileInputStream("D:\\text.txt");
//	BufferedInputStream in2 = new BufferedInputStream(in1);//装饰文件输入流
//	DataInputStream in = new DataInputStream(in2);//装饰缓冲输入流
//}
