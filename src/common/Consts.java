package common;

import java.time.Clock;

public class Consts {
    public static final byte version = 0x00;
    public static final String clientQuitMessage = ":CLIENT_END_CONNECTION";
    public static final String serverQuitMessage = ":SERVER_END_CONNECTION";
    //TODO: Implement Strings as bytes
    public static final int serverPort = 42000;
    public static final Clock systemClock; //Wird für die Log-Methode gebraucht
    static{
        systemClock = Clock.systemUTC();
    }
}
