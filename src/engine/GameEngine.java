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

    }

    public void initializeGame(boolean logToFile) {
        logger = new Logger(logToFile);

    }

    public static boolean gameFull() {
        return (spielerList.size() <= 6);
    }

}
