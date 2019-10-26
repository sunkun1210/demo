package com.sun.reflect;

import java.io.Serializable;
/**
 * 为了便于按照面向对象的方式来处理客户端与服务器的通信
 * 可以把他们发送的信息用Call类表示
 */
public class Call implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String className; //表示类名或接口名
	 
	private String methodName; //表示方法名
	
	private Class[] paramTypes; //表示方法参数类型
	
	private Object[] params; //表示方法参数值
	
	private Object result;//表示方法的执行结果
	

	public Call(String className, String methodName, Class[] paramTypes, Object[] params) {
		super();
		this.className = className;
		this.methodName = methodName;
		this.paramTypes = paramTypes;
		this.params = params;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "Call [className=" + className + ", methodName=" + methodName + "]";
	}
	
}
