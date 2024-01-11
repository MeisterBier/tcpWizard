package common;

public class Karte {
    public final int wert, farbe;

    public Karte(final int pWert, final int pFarbe) {
            wert = pWert;
            farbe = pFarbe;
            //0 Ist ein Narr, 1-13 sind die Zahlenkarten, 14 ist der Zauberer
            //0 - rot, 1 - gelb, 2- gruen, 3 - blau
    }

}
