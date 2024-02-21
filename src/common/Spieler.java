package common;

import networkstack.AnswerWrapper;
import networkstack.ConnectionHandler;
import networkstack.ScreenWrapper;

import java.io.Serializable;
import java.util.ArrayList;

public class Spieler implements Serializable {
    //Informationen über den Spieler
    private final String nickName;
    private int punkte;
    private int sticheVorhergesagt;
    private int sticheGemacht;
    //Das deck eines Spielers wird gesondert über das Netzwerk kopiert.
    private transient ArrayList<Karte> deck;
    //Variablen, die für die Netzwerkverbindung wichtig sind
    private final transient ConnectionHandler spielerConnection;

    public Spieler(String Name, ConnectionHandler connectionHandler){ //Initialisiere Variablen | -1 => nicht belegt
        nickName = Name;
        punkte = 0;
        sticheVorhergesagt = -1;
        sticheGemacht = -1;
        deck = new ArrayList<Karte>();
        spielerConnection = connectionHandler;
    }

    //Get und Set Methoden für die Klassenvariablen
    public void changePunkte(int punkte){
        this.punkte += punkte;
    }

    public int getPunkte(){
        return punkte;
    }

    public String getNickName(){
        return nickName;
    }
    public int getSticheVorhergesagt() {
        return sticheVorhergesagt;
    }

    public void setSticheVorhergesagt(int sticheVorhergesagt) {
        this.sticheVorhergesagt = sticheVorhergesagt;
    }

    public int getSticheGemacht() {
        return sticheGemacht;
    }

    public void setSticheGemacht(int sticheGemacht) {
        this.sticheGemacht = sticheGemacht;
    }

    public void neueRunde(){ //Setzt die Stichvariablen und das Deck zurück
        sticheGemacht = -1;
        sticheVorhergesagt = -1;
        deck = new ArrayList<Karte>();
    }

    //Methoden zum Aufruf von Methoden des ConnectionHandlers.

    public boolean sendToClient(ScreenWrapper request){
        return spielerConnection.sendToClient(request);
    }

    public AnswerWrapper listen(){
        return spielerConnection.listen();
    }

    public void endClient(){
        spielerConnection.endClient();
    }

}
