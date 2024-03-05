package gui;

import common.Spieler;
import networkstack.ScreenWrapper;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Debug {

    //Spieler spieler = new Spieler()
    public Debug(Screen screen){
        ScreenWrapper sw = new ScreenWrapper();
        screen.drawGUI(sw);

        while(true){
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sw = new ScreenWrapper();
            screen.resetButtons();
            screen.drawGUI(sw);
        }
    }
    public static void main(String[] args) {
        Screen screen = new Screen();
        Debug d = new Debug(screen);

    }
}
