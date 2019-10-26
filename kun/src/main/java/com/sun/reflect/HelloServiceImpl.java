package com.sun.reflect;

import java.util.Date;

public class HelloServiceImpl implements HelloService{
	@Override
	public String echo(String name) {
		return "echo:"+name;
	}
	@Override
	public Date getTime() {
		return new Date();
	}
}
