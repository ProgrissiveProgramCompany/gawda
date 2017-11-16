package qmsv2;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
        try {


            ServerSocket ser = new ServerSocket(5000);
            while (true) {

                Socket socket = ser.accept();

                new Threads(socket);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}




