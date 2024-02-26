package networkstack;

import java.io.Serializable;

//Diese Klasse wird vom Client an den Server übergeben.
public class AnswerWrapper implements Serializable {

    private int answerType; //0=> Stiche vorhergesagt, 1=> Karte spielen/Spiel starten
    private int answerValue; //Anzahl der vorhergesagten Stiche, Index der gespielten Karte

    public AnswerWrapper(int answerType, int answerValue){
        this.answerType = answerType;
        this.answerValue = answerValue;
    }

    //Getter für die Klassenvariablen
    public int getAnswerType(){
        return answerType;
    }

    public int getAnswerValue(){
        return answerValue;
    }

}
