package common;

import networkstack.connectionHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class Spieler implements Serializable {
    //Informationen über den Spieler
    final String nickName;
    private int punkte;
    private int sticheVorhergesagt;
    private int sticheGemacht;
    //Das deck eines Spielers wird gesondert über das Netzwerk kopiert.
    private transient ArrayList<Karte> deck;
    //Variablen, die für die Netzwerkverbindung wichtig sind
    private transient socket spielerSocket;
    private transient connectionHandler spielerConnection;




}
