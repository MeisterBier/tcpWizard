package gui;

import common.Spieler;
import networkstack.ScreenWrapper;

public class Debug {

    //Spieler spieler = new Spieler()
    public Debug(Screen screen){
        ScreenWrapper sw = new ScreenWrapper();
        screen.drawGUI(sw);
    }
    public static void main(String[] args) {
        Screen screen = new Screen();
    }
}
