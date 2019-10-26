package com.sun.socket.io2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TCPServer {
    @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6666);
        ThreadPoolExecutor  threadPoolExecutor = new  ThreadPoolExecutor(
        		Runtime.getRuntime().availableProcessors(),
				50, 
				120L, 
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(100));
        while(true) {
            Socket socket = ss.accept();
            threadPoolExecutor.execute(new ServerTask(socket));
           
        }
    }
}