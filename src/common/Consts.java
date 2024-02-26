package common;

import java.time.Clock;

public class Consts {
    public static final int version = 0;
    public static final int nameLength = 12;
    public static final String clientQuitMessage = ":CLIENT_END_CONNECTION";
    public static final String serverQuitMessage = ":SERVER_END_CONNECTION";
    public static final int serverPort = 42000;
    public static final Clock systemClock; //Wird für die Log-Methode gebraucht
    static{
        systemClock = Clock.systemUTC();
    }
    public static final boolean defaultLog = true;
}
