package com.sun.thread;

public class VolatitleNoAtomic extends Thread{
	
	private static volatile int count =0;
	
	public static void addCount() {
		for(int i=0;i<1000;i++) {
			count ++;
		}
		System.out.println(count);
	}
	
	@Override
	public void run() {
		addCount();
	}
	
	public static void main(String[] args) {
		VolatitleNoAtomic[] arr = new VolatitleNoAtomic[10];
		for(int i=0;i<10;i++) {
			arr[i] = new VolatitleNoAtomic();
		}
		
		for(int i=0;i<10;i++) {
			arr[i].start();
		}
	}
}
