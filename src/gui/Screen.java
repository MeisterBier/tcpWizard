package gui;

import javax.swing.*;
import java.awt.*;

public class Wiz extends JFrame {
    public Wiz() {
        setTitle("Wizard - Layout Beispiel");
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
        JPanel untenBereich = new JPanel();
        untenBereich.setBackground(Color.gray);
        rechterSplitPane.setBottomComponent(untenBereich);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Wiz());
    }
}












