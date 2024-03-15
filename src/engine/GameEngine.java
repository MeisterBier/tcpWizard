package engine;

import common.Karte;
import common.Spieler;
import networkstack.ConnectionHandler;
import networkstack.NetworkManager;
import networkstack.ScreenWrapper;

import java.util.ArrayList;

public class GameEngine {
    private static boolean gameStarted;
    private static ArrayList<Spieler> spielerList;
    private static Karte trumph;
    private static int maxRunde, jetztRunde, werDealer, werAmZug;
    private static GameEngine currentGame;


    private static NetworkManager networkManager;

    public GameEngine() {
        gameStarted = false;
        currentGame = this;
        jetztRunde = 0;
        werDealer = 0;
        werAmZug = 0;
        spielerList = new ArrayList<Spieler>();

        setupNetwork();
        waitforStart();
        maxRunde =( 60 / spielerList.size());
        setUpNewRound();

    }
    private void setUpNewRound(){
        jetztRunde++;
        if(jetztRunde > maxRunde) determineWinner();
        else{
            jetztRunde = werDealer = werAmZug = 0;
            firstTurn();

        }


    }
    private void firstTurn(){
        turnSetup();
    }

    private void turnSetup(){
        ArrayList<Karte> stapel = Karte.getShuffledDeck();


        for (Spieler spieler : spielerList) {
            spieler.setDeck(new ArrayList<Karte>());
            for(int i = 0; i<jetztRunde; i++){
               spieler.getDeck().add(stapel.remove(i));
            }
            if(jetztRunde == maxRunde) trumph = new Karte(-1,-1);
            else{
                Karte trumphKarte = stapel.remove(0);
                if(trumphKarte.wert == 1){

                } else if (trumphKarte.wert == 14) {

                } else trumph = trumphKarte;
            }

        }
    }

    private void determineWinner(){

    }

    private int rechtsVon(int linkerSpieler){
        if(++linkerSpieler >= spielerList.size()) return 0;

        else return (linkerSpieler);
    }


    private void setupNetwork(){
        networkManager = new NetworkManager();
    }

    private void waitforStart(){
        while(!gameStarted){
            if(spielerList.size() >= 3){
                if(spielerList.get(0).listen().getAnswerValue() == 0) gameStarted = true;
            }
        }
    }

    private void neuerSpieler(String name, ConnectionHandler connection){
            if(spielerList.size() < 7)spielerList.add(new Spieler(name, connection));
            else connection.endClient();
            callOutNewPlayers();
    }

    private void callOutNewPlayers(){
        if(spielerList.get(0).sendToClient(new ScreenWrapper(-1 * spielerList.size())));
        else {
            spielerList.get(0).endClient();
            spielerList.remove(0);
            if(!spielerList.isEmpty()) callOutNewPlayers();
        }

    }

    public static boolean gameFull() {
        return !(spielerList.size() <= 6);
    }

    public static boolean nameAvailiable(String name){
        for(Spieler i : spielerList){
            if(i.getNickName().equalsIgnoreCase(name)) return false;
        }
        return true;
    }

    public static boolean gameRunning(){
        return(gameStarted);
    }
    public static GameEngine getCurrentGame(){
        return currentGame;
    }

}
