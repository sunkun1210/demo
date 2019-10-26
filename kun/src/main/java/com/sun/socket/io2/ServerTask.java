package com.sun.socket.io2;

import java.io.DataInputStream;
import java.net.Socket;

public class ServerTask implements Runnable {

	private Socket socket;
	public ServerTask (Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("a client connect!");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            System.out.println(dis.readUTF());
            dis.close();
            socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
