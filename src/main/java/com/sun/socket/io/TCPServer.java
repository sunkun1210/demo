package com.sun.socket.io;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6666);
        /***
         * 一个客户端连上来以后
         * 在while循环里面处理客户端通信
         * 在通信的过程中 另外一个客户端连接不上来
         */
        while(true) {
            /***
             * 如果没有客户端连接 这行代码就会一直阻塞在这
             */
            Socket s = ss.accept();
            System.out.println("a client connect!");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            /***
             * 一个客户端连上来 迟迟不发东西 程序就在这阻塞住了
             * 另一个客户端连的时候 必须经过accept方法，而accept方法必须在下次循环的时候才进的来
             */
            System.out.println(dis.readUTF());
            dis.close();
            s.close();
        }
    }
}