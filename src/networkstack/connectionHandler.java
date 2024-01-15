package networkstack;

import java.io.*;
import java.net.Socket;

import common.Consts;

public class connectionHandler {
    private final PrintWriter textOut;
    private final BufferedReader textIn;
    private final ObjectOutputStream objectOut;
    private final ObjectInputStream objectIn;
    private final Socket objectSocket;
    private final Socket infoSocket;

    private boolean isRunning;


    public connectionHandler(Socket oS, Socket iS) throws IOException {
        objectSocket = oS;
        infoSocket = iS;
        objectOut = new ObjectOutputStream(objectSocket.getOutputStream());
        objectIn = new ObjectInputStream(objectSocket.getInputStream());
        textOut = new PrintWriter(infoSocket.getOutputStream(), true);
        textIn = new BufferedReader(new InputStreamReader(infoSocket.getInputStream()));

        isRunning = true;
        CrashListener listener = new CrashListener();
        Thread crashListenerThread = new Thread(listener);
        crashListenerThread.start();

    }

    public boolean sendToClient(screenWrapper request){
      if(isRunning) {
          try {
              objectOut.writeObject(request);
              objectOut.flush();
              return true;
          } catch (Exception e) {
              crash();
              return false;
          }
      }
      else return false;
    }

    private AnswerWrapper listen(){
        if(!isRunning) return null;
        try{
            return (AnswerWrapper) objectIn.readObject();
        } catch (Exception e){
            crash();
            return null;
        }
    }


    private void crash(){
        if(isRunning)
            try{
                  objectIn.close();
                  objectOut.close();
                  if(!objectSocket.isClosed())objectSocket.close();

            } catch(Exception e){
                //Do nothing
            }
        isRunning = false;
    }

    public void encClient(){
        textOut.println(Consts.serverQuitMessage);
        crash();
    }

    private class CrashListener implements Runnable{
        public CrashListener(){

        }

        @Override
        public void run(){
            while(isRunning){
                String input = "";
                try {
                    input = textIn.readLine();
                } catch (IOException e) {
                    crash();
                }
                if(input != null) {
                    if(input.equals(Consts.clientQuitMessage)){
                        playerDisconnect();
                        crash();
                    }
                }
            }
        }
    }
}
