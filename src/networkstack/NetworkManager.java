package networkstack;
import com.sun.security.ntlm.Client;
import common.Consts;
import engine.GameEngine;
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
        private ServerSocket server, s1, s2;
        private boolean succesfullRequest;

        private ServerThread(ServerSocket server) {
            this.server = server;
            succesfullRequest = false;
            Thread myThread = new Thread(this);
            myThread.start();
        }

        @Override
        public void run(){
            if(isRunning) {
                try {
                    Socket client = server.accept();
                    ServerThread st = new ServerThread(server);
                    Logger.getInstance().log("Connection established with " + client.getRemoteSocketAddress().toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    boolean requestProcessed = false;
                    String loginMessage;
                    while (isRunning && !requestProcessed) {
                        loginMessage = in.readLine();
                        if (loginMessage != null) {
                            if (!loginMessage.isEmpty()) {
                                ArrayList<String> loginCommand = new ArrayList<String>(Arrays.asList(loginMessage.split(";")));
                                out.println(generateLoginResponse(loginCommand));
                                //Beendet die Verbindung
                                if (requestProcessed = true) {
                                    in.close();
                                    out.close();
                                    client.close();
                                    if (succesfullRequest) {
                                        ClientConnector clientConnector = new ClientConnector(s1, s2);
                                        Socket[] clientSockets = clientConnector.getSockets();
                                        if (clientSockets != null) {
                                            Logger.getInstance().log("Succesfully made connection with player \"" + loginCommand.get(1) + "\" at " + clientSockets[0].getRemoteSocketAddress().toString());
                                            //TODO Neuer Spieler Methode abrufen
                                        }

                                    }
                                }
                                //Hier endet der Thread
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //TODO: Handle
                }
            }

        }

        private String generateLoginResponse(ArrayList<String> loginCommand){
            /*
            Eingabe:
            Ein String mit mehreren Werten, getrennt von Semicoli:
            [0] Version des Clients: ein Byte, korrespondiert mit Consts.version
            [1] Spielername: ein String



            Antwort:
            Ein String mit mehreren Werten, getrennt von Semicoli:
            [0]: Der Antwortwert:
                1: Verbindung angenommen, jetzt folgen in [1] und [2] zwei Ports für die Verbindung
                0: Verbindung abgelehnt, Anfrage nicht erkannt
                2: Verbindung abgelehnt, Spiel voll
                3: Verbindung abgelehnt, Name schon vergeben
                4: Verbindung abgelehnt, falsche Version, jetzt folgt in [1] die Version des Servers
                5: Verbindung abgelehnt, Name entspricht nicht den Regeln
                6: Verbindung abgelehnt, Spiel läuft schon
             */


            //Schaut, ob die Eingabe dem Protokoll entspricht.
            try {
                if (loginCommand.size() != 2) {
                    return "0";
                } else if(GameEngine.gameRunning()){
                    return "6";
                } else if (engine.GameEngine.gameFull()) {
                    return "2";
                } else if (Integer.parseInt(loginCommand.get(0)) != Consts.version) {
                    return ("4;" + Consts.version);
                } else if (loginCommand.get(1).contains(" ") | loginCommand.get(1).length() > Consts.nameLength) {
                    return "5";
                } else if (!GameEngine.nameAvailiable(loginCommand.get(1))) {
                    return "3";
                }
            } catch (Exception e){
                    Logger.getInstance().log("Login by player failed, unknown Exception." );
                    return "0";
            }

            try {
                s1 = new ServerSocket(0);
                s2 = new ServerSocket(0);
                Logger.getInstance().log("Trying to establish connections on Ports " + s1.getLocalPort() + "/" + s2.getLocalPort());
                succesfullRequest = true;
                return ("1;" + s1.getLocalPort() + ";" + s2.getLocalPort());
            } catch (Exception e){
                Logger.getInstance().log("Couldn't find two free ports");
                return "0";
           }


        }

    }
    //This may or may not work :(
    //Update: It does :)
    private class ClientConnector{

        Socket[] sockets;
        ServerSocket ss1, ss2;
        ServerSocketWaiter[] serverSocketWaiters;
        Thread thread1, thread2;
        boolean isInterrupted;

        private ClientConnector(ServerSocket ss1, ServerSocket ss2){
            this.ss1 = ss1;
            this.ss2 = ss2;
            sockets = new Socket[2];
            Arrays.fill(sockets, null);
            serverSocketWaiters = new ServerSocketWaiter[2];
            serverSocketWaiters[0] = new ServerSocketWaiter(ss1, 0, this);
            serverSocketWaiters[1] = new ServerSocketWaiter(ss2, 1, this);
        }
        private Socket[] getSockets(){
            thread1 = new Thread(serverSocketWaiters[0]);
            thread2 = new Thread(serverSocketWaiters[1]);
            thread1.start();
            thread2.start();

            while(sockets[0] == null || sockets[1] == null){
                if(isInterrupted) return null;
            }
            forceStop();
            return sockets;
        }

        private void setSockets(int index, Socket s){
            sockets[index] = s;
        }

        private void forceStop(){
                isInterrupted = true;
                try {
                    ss1.close();
                }catch (Exception e){
                 //Ignore
                }
                try {
                    ss1.close();
                }catch (Exception e){
                //Ignore
                }
        }
        private class ServerSocketWaiter implements Runnable{
            ServerSocket ss;
            int index;
            ClientConnector chef;
            ServerSocketWaiter(ServerSocket ss, int index, ClientConnector chef){
                this.ss = ss;
                this.index = index;
                this.chef = chef;
            }
            @Override
            public void run(){
                try{
                    Socket s = ss.accept();
                    chef.setSockets(index, s);

                } catch (Exception e){
                    if(!isInterrupted) {
                        Logger.getInstance().log("Exception while waiting for client to connect");
                        isInterrupted = true;
                        chef.forceStop();
                    }
                }
            }
        }

    }

}
