package com.sun.socket.io;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 8001);
        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        Thread.sleep(10000);
        dos.writeUTF("hello server!");
        dos.flush();
        dos.close();
        s.close();
    }
}