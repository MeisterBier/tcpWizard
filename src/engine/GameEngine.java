package engine;

import common.Spieler;

import java.util.ArrayList;

public class GameEngine {
    private boolean gameStarted;
    private int spielerAnzahl;
    Logger logger;
    private static ArrayList<Spieler> spielerList;
    private boolean fileLog;

    public GameEngine() {
        spielerList = new ArrayList<Spieler>();
    }

    public void initializeGame(boolean logToFile) {
        logger = new Logger(logToFile);

    }

    public static boolean gameFull() {
        return !(spielerList.size() <= 6);
    }

    public static boolean nameAvailiable(String name){
        if(spielerList.isEmpty()) return true;
        for(int i = 0; i<spielerList.size(); i++){
            if(spielerList.get(i).getNickName().equalsIgnoreCase(name)) return false;
        }
        return true;
    }

}
