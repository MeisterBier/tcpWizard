//A wrapper class for method calls to be sent over the network.
// Can be serialized on one side and deserialized on the other.

package networkstack;

import common.*;

import java.io.Serializable;

public class screenWrapper implements Serializable {

    final String methodType;
    private int spieler, meineSpielerNummer, runde, amZug, dealer, maxRunde;
    private String[] namen;
    private int[] punkte, vorhergesagt, gemacht;
    private Karte[] hand, aufTisch;
    private Karte trumph;






    public screenWrapper(int spieler, int meineSpielerNummer, String[] namen, int[] punkte, int amZug, int dealer, int[] vorhergesagt, int[] gemacht, Karte[] hand, int runde, int maxRunde){
        this.spieler = spieler;
        this.meineSpielerNummer = meineSpielerNummer;
        this.namen = namen;
        this.punkte = punkte;
        this.amZug = amZug;
        this.dealer = dealer;
        this.vorhergesagt = vorhergesagt;
        this.gemacht = gemacht;
        this.hand = hand;
        this.runde = runde;
        this.maxRunde = maxRunde;
        this.methodType = "Phase1";
    }

    public screenWrapper(int spieler, int meineSpielerNummer, String[] namen, int[] punkte, int amZug, int dealer, int[] vorhergesagt, int[] gemacht, Karte[] hand, int runde, int maxRunde, Karte trumph, Karte[] aufTisch){
        this.spieler = spieler;
        this.meineSpielerNummer = meineSpielerNummer;
        this.namen = namen;
        this.punkte = punkte;
        this.amZug = amZug;
        this.dealer = dealer;
        this.vorhergesagt = vorhergesagt;
        this.gemacht = gemacht;
        this.hand = hand;
        this.runde = runde;
        this.maxRunde = maxRunde;
        this.trumph = trumph;
        this.aufTisch = aufTisch;
        this.methodType = "Phase2";
    }

    //Hier müssen noch die Anfangs- bzw. Endphase sowie alle Popups integriert werden.
    //TODO: Continue




}
