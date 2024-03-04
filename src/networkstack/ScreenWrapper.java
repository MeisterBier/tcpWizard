//A wrapper class for method calls to be sent over the network.
// Can be serialized on one side and deserialized on the other.

package networkstack;

import common.*;

import java.io.Serializable;
import java.util.ArrayList;

public class ScreenWrapper implements Serializable {

    //Variablen für eine Spielrunde
    private int spielPhase;  // 0=> Stiche werden vorhergesagt, 1 => Stiche werden gemacht
    private int runde;
    private int spielerDran;
    private ArrayList<Spieler> spieler;
    private ArrayList<Karte> hand;
    private Karte trumph;


    //Variablen für die Popups
    private boolean isPopup;
    private int popupType; //-1 => Ungültiger Zug, -2 => Stiche vorhersagen, i => Stiche vorhersagen (i nicht erlaubt)

    //Constructor für Spielrunden
    public ScreenWrapper(int spielPhase, int runde, int spielerDran, ArrayList<Spieler> spieler, ArrayList<Karte> hand, Karte trumph){
        this.spielPhase = spielPhase;
        this.runde = runde;
        this.spielerDran = spielerDran;
        this.spieler = spieler;
        this.hand = hand;
        this.trumph = trumph;
    }

    public ScreenWrapper(){
        isPopup=false;
        hand = new ArrayList<Karte>();
        ArrayList<Karte> deck = Karte.getShuffledDeck();
        for(int i = 0; i<4; i++){
            hand.add(deck.get(i));
        }
    }

    //Constructor für Popups:
    public ScreenWrapper(int popupType){
        this.isPopup = true;
        this.popupType = popupType;
    }

    //Getter für die Klassenvariablen

    public int getSpielPhase() {
        return spielPhase;
    }

    public int getRunde() {
        return runde;
    }

    public int getSpielerDran() {
        return spielerDran;
    }

    public ArrayList<Spieler> getSpieler() {
        return spieler;
    }

    public ArrayList<Karte> getHand() {
        return hand;
    }

    public boolean isPopup() {
        return isPopup;
    }

    public int getPopupType() {
        return popupType;
    }

    public Karte getTrumph() {
        return trumph;
    }
}
