package networkstack;
import common.Consts;
import engine.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class NetworkManager {
    private boolean isRunning;
    public NetworkManager(){
        ServerSocket server;
        try{
            server = new ServerSocket(Consts.serverPort);
            Logger.getInstance().log("Started server on port " + Consts.serverPort);
            isRunning = true;
            ServerThread st = new ServerThread(server);
        } catch (Exception e){
            Logger.getInstance().log("Couldn't start server on port  " + Consts.serverPort);
            isRunning = false;
        }
    }
    public boolean getIsRunning(){
        return isRunning;
    }

    private class ServerThread implements Runnable{
        ServerSocket server;
        private ServerThread(ServerSocket server){
            this.server = server;
            Thread myThread = new Thread(this);
            myThread.start();
        }

        @Override
        public void run(){
            while(isRunning){
                try{
                    Socket client = server.accept();
                    ServerThread st = new ServerThread(server);
                    Logger.getInstance().log("Connection established with " + client.getRemoteSocketAddress().toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    boolean requestProcessed = false;
                    String loginMessage;
                    while(isRunning && !requestProcessed){
                        loginMessage = in.readLine();
                        if(loginMessage != null){
                            if(!loginMessage.isEmpty()){
                                ArrayList<String> loginCommand = (ArrayList<String>) Arrays.asList(loginMessage.split(";"));
                                out.println(generateLoginResponse(loginCommand));
                                //Beendet die Verbindung
                                requestProcessed = true;
                                in.close();
                                out.close();
                                client.close();
                                //Hier endet der Thread
                            }
                        }

                    }
                } catch (Exception e){
                    //TODO: Handle

                }
            }
        }

        private String generateLoginResponse(ArrayList<String> loginCommand){
            /*
            Eingabe:
            Ein String mit mehreren Werten, getrennt von Semicoli:
            [0] Version des Clients: ein Byte, correspondiert mit Consts.version
            [1] Spielername: ein String



            Antwort:
            Ein String mit mehreren Werten, getrennt von Semicoli:
            [0]: Der Antwortwert:
                1: Verbindung angenommen, jetzt folgen in [1] und [2] zwei Ports für die Verbindung
                0: Verbindung abgelehnt, Anfrage nicht erkannt
                2: Verbindung abgelehnt, Spiel voll
                3: Verbindung abgelehnt, Name schon vergeben
                4: Verbindung abgelehnt, falsche Version, jetz folgt in [1] die Version des Servers
                5: Verbindung abgelehnt, Name entspricht nicht den Regeln
             */

            String response;
            response = "";
            return response;
        }

    }
}
