package networkstack;

import java.net.ServerSocket;
import java.net.Socket;

public class NewPlayerThread {

    boolean isRunning;
    ServerSocket server;
public NewPlayerThread(int port){
    isRunning = true;
    try{
        server = new ServerSocket(port);
    } catch (Exception e){
        isRunning = false;
        e.printStackTrace();
    }
    while(isRunning){
        try{
            Socket clientSocket = server.accept();
        } catch (Exception e){
            isRunning = false;
            e.printStackTrace();
        }

    }



}



}

