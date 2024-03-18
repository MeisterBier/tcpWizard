package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Karte implements Serializable {
    public final int wert, farbe;

    //Ein fertig erstelltes Kartendeck, damit der untenstehende Code nicht bei jeder neuen Runde ausgeführt werden muss.
    public static final ArrayList<Karte> orderedDeck;
    static{
        ArrayList<Karte> deck = new ArrayList<>();
        for(int i = 0; i<4; i++){
            for(int j = 0; j<15; j++){
                deck.add(new Karte(j,i));
            }
        }
        orderedDeck = deck;
    }

    public Karte(final int pWert, final int pFarbe) {
            wert = pWert;
            farbe = pFarbe;
            //0 Ist ein Narr, 1-13 sind die Zahlenkarten, 14 ist der Zauberer
            //0 - rot, 1 - gelb, 2- grün, 3 - blau
    }


    public static ArrayList<Karte> getShuffledDeck(){ //Gibt ein durchgemischtes Kartendeck zurück
        ArrayList<Karte> shuffledDeck = new ArrayList<>(orderedDeck);
        Collections.shuffle(shuffledDeck);
        return shuffledDeck;
    }



}
