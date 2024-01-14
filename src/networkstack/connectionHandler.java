package networkstack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class connectionHandler {
    private final ObjectOutputStream objectOut;
    private final ObjectInputStream objectIn;
    private final Socket socket;

    private boolean isRunning;


    public connectionHandler(Socket s) throws IOException {
        socket = s;
        objectOut = new ObjectOutputStream(socket.getOutputStream());
        objectIn = new ObjectInputStream(socket.getInputStream());
        isRunning = true;
    }

    public boolean sendToClient(protocolRequest request){
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

    private void crash(){
        if(isRunning)
            try{
                  objectIn.close();
                  objectOut.close();
                  if(!socket.isClosed())socket.close();

            } catch(Exception e){
                //Do nothing
            }
        isRunning = false;
    }


}
