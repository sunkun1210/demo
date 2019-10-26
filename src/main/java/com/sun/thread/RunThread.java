package com.sun.thread;

public class RunThread extends Thread{
	
	private volatile Boolean isRunning=true;

	public void setIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	@Override
	public void run() {
		System.out.println("进入run方法");
		while(isRunning==true) {
			
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args)throws Exception {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(300);
		rt.setIsRunning(false);
		System.out.println("isRuning已经设置false");
		Thread.sleep(1000);
		System.out.println(rt.isRunning);
	}
	
}
