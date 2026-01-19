package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        JFrame window = new JFrame("Simple Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        window.setResizable(false); // prevent frame from being resized by users

        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.launchGame();
        // window.setSize(420,420);


    }
}