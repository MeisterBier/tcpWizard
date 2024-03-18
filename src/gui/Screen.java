package gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.util.ArrayList;

import common.Karte;
import networkstack.ScreenWrapper;


public class Screen extends JFrame {

    Karte testCard = new Karte(2,4);
    public JButton[] buttons = new JButton[20];
    JButton btn = new JButton();
    JPanel handBereich = new JPanel();


    public Screen() {
        setTitle("Wizard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // SplitPane für vertikalen Split (links - rechts)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setEnabled(false); // Deaktiviere die Möglichkeit, den Haupt-SplitPane zu verschieben
        splitPane.setDividerSize(0); // Entferne den Divider zwischen den Split-Screens

        // Linker Bereich für Punkte
        JPanel punkteBereich = new JPanel();
        punkteBereich.setBackground(Color.darkGray);
        punkteBereich.setPreferredSize(new Dimension(150, getHeight())); // Breite einstellen
        splitPane.setLeftComponent(punkteBereich);

        // Rechter Bereich für Karten (oben - unten)
        JSplitPane rechterSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        rechterSplitPane.setEnabled(false); // Deaktiviere die Möglichkeit, den inneren SplitPane zu verschieben
        rechterSplitPane.setDividerSize(0); // Entferne den Divider zwischen den Split-Screens

        // Oberer Bereich für gespielte Karten
        JPanel obenBereich = new JPanel();
        obenBereich.setBackground(Color.lightGray);
        rechterSplitPane.setTopComponent(obenBereich);

        // Unterer Bereich für Karten auf der Hand

        handBereich.setBackground(Color.gray);
        addBtns();

        handBereich.setLayout(new GridLayout(2,10));
        rechterSplitPane.setBottomComponent(handBereich);

        // Einstellungen für den vertikalen Split im rechten Bereich
        rechterSplitPane.setDividerLocation(300); // Anfangsposition des Dividers
        rechterSplitPane.setResizeWeight(0.5); // Gewichtung der Größe

        // Rechten Bereich dem Haupt-SplitPane hinzufügen
        splitPane.setRightComponent(rechterSplitPane);

        // Einstellungen für den vertikalen Split im Haupt-SplitPane
        splitPane.setDividerLocation(150); // Anfangsposition des Dividers

        add(splitPane);

        setVisible(true);
    }

    public void drawGUI(ScreenWrapper screenWrapper){
        if(screenWrapper.isPopup()){
            //TODO implementieren
        }
        else{
            //Initialisiere die Hand
            int i = 0;
            for(Karte k: screenWrapper.getHand()){
                setBtn(buttons[i], k.farbe, k.wert);
                ++i;
            }

            //Initialisiere die Anderen Spieler

            //Initialisiere den Tisch

            //Initialisiere den Trumph
        }
    }


    public void setBtn(JButton btn, int farbe, int wert){
        System.out.println(farbe + "\t" + wert);
        switch(farbe){
            //0 - rot, 1 - gelb, 2- grün, 3 - blau
            case 0:
                btn.setBackground(Color.red);
                break;
            case 1:
                btn.setBackground(Color.yellow);
                break;
            case 2:
                btn.setBackground(Color.green);
                break;
            case 3:
                btn.setBackground(Color.blue);
                break;
        }
        btn.setText(Integer.toString(wert));
        if(wert == 14){
            btn.setBackground(Color.white);
            btn.setText("Whisard");
        } else if (wert == 0){
            btn.setBackground(Color.white);
            btn.setText("Clown");
        }
        btn.setOpaque(true);
        btn.setBorderPainted(true);
        btn.setContentAreaFilled(true);


    }

    void resetButtons(){
        for(int i = 0; i< buttons.length; i++){
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setText("");
        }
    }
    public void addBtns(){
        for (int i = 0; i < buttons.length; i++){
            buttons[i] = new JButton();
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            handBereich.add(buttons[i]);
            System.out.println(buttons);

        }
//        System.out.println(sw.getHand());
//        for (int j = 0; j < 4; j++){
//            buttons[j].setOpaque(true);
//            buttons[j].setContentAreaFilled(true);
//            buttons[j].setBorderPainted(true);
//        }



    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Screen());
    }
}












